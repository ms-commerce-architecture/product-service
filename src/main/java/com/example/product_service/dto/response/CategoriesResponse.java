package com.example.product_service.dto.response;

import com.example.product_service.model.Enums.CategoryStatus;

import java.math.BigInteger;

public record CategoriesResponse( String categoryId,
         String categoryName,
         String parentCategoryId,
         String description,
         CategoryStatus status) {
}
