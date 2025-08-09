package com.example.product_service.service.interfaces;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.ProductAttributes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(String id);

    Page<ProductResponse> getAllProducts(int page, int size, String categoryId);

    ProductResponse updateProduct(String id, ProductRequest productRequest);

    void deleteProduct(String id);

    List<ProductAttributes> getProductAttributes(String productId);

    Page<ProductResponse> searchProducts(String query, int page, int size);
}
