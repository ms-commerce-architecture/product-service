package com.example.product_service.repository;

import com.example.product_service.model.ProductAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductAttributesRepository extends MongoRepository<ProductAttributes, String> {
}
