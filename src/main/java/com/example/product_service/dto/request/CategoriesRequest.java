package com.example.product_service.dto.request;

import com.example.product_service.model.Enums.CategoryStatus;


public record CategoriesRequest(String categoryId, String categoryName,
                                String parentCategoryId,
                                String description,
                                CategoryStatus status) {
}
