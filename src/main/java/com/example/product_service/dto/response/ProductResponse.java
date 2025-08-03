package com.example.product_service.dto.response;

import com.example.product_service.model.Enums.ProductStatus;

import java.math.BigDecimal;

public record ProductResponse (String id, String productName,
                               String productCode,
                               String description,
                               String categoryId,
                               BigDecimal price,
                               BigDecimal costPrice,
                               BigDecimal weight,
                               String dimensions,
                               ProductStatus status) {
}
