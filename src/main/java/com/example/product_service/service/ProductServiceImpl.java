package com.example.product_service.service;



import com.example.product_service.client.InventeryClient;
import com.example.product_service.dto.request.AttributesRequest;
import com.example.product_service.dto.request.InitialInventoryRequest;
import com.example.product_service.dto.request.InventoryCreateRequest;
import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.AttributesResponse;
import com.example.product_service.dto.response.InventoryCreateResponse;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.event.ProductCreatedEvent;
import com.example.product_service.exceptions.ResourceNotFoundException;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductAttributes;
import com.example.product_service.repository.ProductAttributesRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoriesService categoriesService;
    private final InventeryClient inventeryClient;
    private final ProductAttributesRepository productAttributesRepository;
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Transactional
    @Override public ProductResponse createProduct(ProductRequest productRequest) {

        categoriesService.validateCategoryExists(productRequest.categoryId());


        Product product = Product.builder()
                .productName(productRequest.productName())
                .description(productRequest.description())
                .categoryId(productRequest.categoryId())
                .price(productRequest.price())
                .costPrice(productRequest.costPrice())
                .weight(productRequest.weight())
                .dimensions(productRequest.dimensions())
                .productCode(productRequest.productCode())
                .status(productRequest.status())
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product created: {}", savedProduct.getId());

        createAttributes(savedProduct.getId(), productRequest.attributes());


        if (productRequest.initialInventory() != null && !productRequest.initialInventory().isEmpty()) {
            createInitialInventory(savedProduct.getId(), productRequest.initialInventory());
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productRequest.productName(), productRequest.productCode());
        kafkaTemplate.send("product-notification", productCreatedEvent);
        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getProductName(),
                savedProduct.getProductCode(),
                savedProduct.getDescription(),
                savedProduct.getCategoryId(),
                savedProduct.getPrice(),
                savedProduct.getCostPrice(),
                savedProduct.getWeight(),
                savedProduct.getDimensions(),
                savedProduct.getStatus()
        );
    }



    @Override public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return mapToProductResponse(product);
    }

    @Override public Page<ProductResponse> getAllProducts(int page, int size, String categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;

        if (categoryId != null && !categoryId.isEmpty()) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(this::mapToProductResponse);
    }

    @Override public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));


        if (!existingProduct.getCategoryId().equals(productRequest.categoryId())) {
            categoriesService.validateCategoryExists(productRequest.categoryId());
        }


        existingProduct.setProductName(productRequest.productName());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setCategoryId(productRequest.categoryId());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setCostPrice(productRequest.costPrice());
        existingProduct.setWeight(productRequest.weight());
        existingProduct.setDimensions(productRequest.dimensions());
        existingProduct.setStatus(productRequest.status());

        Product updatedProduct = productRepository.save(existingProduct);



        return mapToProductResponse(updatedProduct);
    }

    @Override public void deleteProduct(String id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }


        productAttributesRepository.deleteByProductId(id);


        productRepository.deleteById(id);

//        kafkaTemplate.send("product-notification", new ProductDeletedEvent(id));
    }

    @Override public List<ProductAttributes> getProductAttributes(String productId) {
        return productAttributesRepository.findByProductId(productId);
    }



    @Override public Page<ProductResponse> searchProducts(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.search(
                query.toLowerCase(),
                pageable);

        return products.map(this::mapToProductResponse);
    }


    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getDescription(),
                product.getCategoryId(),
                product.getPrice(),
                product.getCostPrice(),
                product.getWeight(),
                product.getDimensions(),
                product.getStatus()
        );
    }

    private void createInitialInventory(String productId, List<InitialInventoryRequest> initialInventory) {
        long successCount = 0;

        for (InitialInventoryRequest inventory : initialInventory) {
            try {
                InventoryCreateRequest request = new InventoryCreateRequest(
                        productId,
                        inventory.warehouseId(),
                        inventory.initialQuantity(),
                        0,
                        inventory.initialQuantity(),
                        inventory.reorderLevel(),
                        inventory.maxStockLevel()
                );

                InventoryCreateResponse response = inventeryClient.createInventory(request);

                if (response.created()) {
                    successCount++;
                } else {
                    log.warn("Inventory creation failed for product: {}, warehouse: {} - {}",
                            response.productId(), response.warehouseId(), response.message());
                }

            } catch (Exception e) {
                log.error("Exception while creating inventory for product: {}, warehouse: {}",
                        productId, inventory.warehouseId(), e);
            }
        }

        log.info("Initial inventory creation completed for product: {} - {}/{} successful",
                productId, successCount, initialInventory.size());
    }


    private void createAttributes(String productId, List<AttributesRequest> attributesRequest) {

        List<ProductAttributes> attributesToSave = new ArrayList<>();

        for (AttributesRequest request : attributesRequest) {

            ProductAttributes attribute = ProductAttributes.builder()
                    .productId(productId)
                    .attributeName(request.attribute_name())
                    .attributeValue(request.attribute_value())
                    .build();


            attributesToSave.add(attribute);
        }


        productAttributesRepository.saveAll(attributesToSave);


    }

}
