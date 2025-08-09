package com.example.product_service.dto.response;


public record InventoryCreateResponse(
        String productId,
        Long warehouseId,
        boolean created,
        String message
) {}