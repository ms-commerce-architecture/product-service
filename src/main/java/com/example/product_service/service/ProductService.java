package com.example.product_service.service;


import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private  final CategoriesService categoriesService;
    public ProductResponse createProduct(ProductRequest productRequest) {
        categoriesService.validateCategoryExists(productRequest.categoryId());
        Product product = Product.builder().productName(productRequest.productName())
                .description(productRequest.description())
                .categoryId(productRequest.categoryId())
                .price(productRequest.price())
                .costPrice(productRequest.costPrice())
                .weight(productRequest.weight())
                .dimensions(productRequest.description())
                .productCode(productRequest.productCode())
                .status(productRequest.status())
                .build();

       Product product1= productRepository.save(product);
        log.info("Product created");
        log.info(product1.toString());
        return new ProductResponse(product1.getId(),product1.getProductName(),product1.getProductCode(),product1.getDescription(),product1.getCategoryId(),product1.getPrice(),product1.getCostPrice(),product1.getWeight(),product1.getDimensions(),product1.getStatus());
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),product.getProductName(),product.getProductCode(),product.getDescription(),product.getCategoryId(),product.getPrice(),product.getCostPrice(),product.getWeight(),product.getDimensions(),product.getStatus())).collect(Collectors.toList());
    }

}
