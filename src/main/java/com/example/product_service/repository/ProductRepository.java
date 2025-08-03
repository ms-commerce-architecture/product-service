package com.example.product_service.repository;

import com.example.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ProductRepository extends MongoRepository<Product, String> {

}
