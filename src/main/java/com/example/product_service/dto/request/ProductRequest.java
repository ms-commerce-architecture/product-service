package com.example.product_service.dto.request;

import com.example.product_service.model.Enums.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(  String id,String productName,
         String productCode,
         String description,
         String categoryId,
         BigDecimal price,
         BigDecimal costPrice,
         BigDecimal weight,
         String dimensions,
         ProductStatus status, List<InitialInventoryRequest> initialInventory,


                               List<AttributesRequest> attributes
) {
}
