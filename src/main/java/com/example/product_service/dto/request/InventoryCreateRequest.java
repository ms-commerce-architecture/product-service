package com.example.product_service.dto.request;



public record InventoryCreateRequest(
        String productId,
        Long warehouseId,
        Integer availableQuantity,
        Integer reservedQuantity,
        Integer totalQuantity,
        Integer reorderLevel,
        Integer maxStockLevel
) {}
