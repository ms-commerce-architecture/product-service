package com.example.product_service.service.interfaces;

import com.example.product_service.dto.request.CategoriesRequest;
import com.example.product_service.dto.response.CategoriesResponse;

import java.util.List;

public interface CategoriesService {


    CategoriesResponse createCategories(CategoriesRequest categoriesRequest);

    CategoriesResponse updateCategory(String categoryId, CategoriesRequest request);

    void deleteCategory(String categoryId);

    CategoriesResponse getCategoryById(String categoryId);

    List<CategoriesResponse> getAllCategories();

    void validateCategoryExists(String categoryId);
}
