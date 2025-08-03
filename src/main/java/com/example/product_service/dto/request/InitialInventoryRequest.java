package com.example.product_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InitialInventoryRequest(
        @NotNull
        Long warehouseId,

        @NotNull
        @Min(0)
        Integer initialQuantity,

        @Min(0)
        Integer reorderLevel,

        Integer maxStockLevel
) {}
