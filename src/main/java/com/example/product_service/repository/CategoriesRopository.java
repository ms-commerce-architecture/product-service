package com.example.product_service.repository;

import com.example.product_service.model.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRopository extends MongoRepository<Categories, String> {

}
