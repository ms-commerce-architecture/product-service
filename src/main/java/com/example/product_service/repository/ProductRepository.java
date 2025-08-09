package com.example.product_service.repository;

import com.example.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
   Page<Product> findByCategoryId(String categoryId, Pageable pageable);
  Page<Product> search(String keyword, Pageable pageable);

}
