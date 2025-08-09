package com.example.product_service.repository;

import com.example.product_service.model.ProductAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductAttributesRepository extends MongoRepository<ProductAttributes, String> {
    void deleteByProductId(String productId);

    List<ProductAttributes> findByProductId(String productId);
}
