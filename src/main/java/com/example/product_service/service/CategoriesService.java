package com.example.product_service.service;


import com.example.product_service.dto.request.CategoriesRequest;
import com.example.product_service.dto.response.CategoriesResponse;
import com.example.product_service.exceptions.ResourceNotFoundException;
import com.example.product_service.model.Categories;
import com.example.product_service.repository.CategoriesRopository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRopository categoriesRepository;


    public CategoriesResponse createCategories(CategoriesRequest categoriesRequest) {
        Categories categories =  Categories.builder().categoryName(categoriesRequest.categoryName()).description(categoriesRequest.description())
                .parentCategoryId(categoriesRequest.parentCategoryId()).status(categoriesRequest.status()).build();
       Categories categoriesResponse = categoriesRepository.save(categories);
       return new CategoriesResponse(categoriesResponse.getCategoryId(),categoriesResponse.getCategoryName(),categoriesResponse.getParentCategoryId(),categoriesResponse.getDescription(),categoriesResponse.getStatus());

    }

    public void validateCategoryExists(String categoryId) {
        if (!categoriesRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category ID not found: " + categoryId);
        }
    }

}
