package com.example.product_service.client;


import com.example.product_service.dto.request.InventoryCreateRequest;
import com.example.product_service.dto.response.InventoryCreateResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.service.annotation.PostExchange;

public interface InventeryClient {

    Logger log = LoggerFactory.getLogger(InventeryClient.class);
    @PostExchange("/api/ineventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackCreateInventory")
    @Retry(name = "inventory")
    InventoryCreateResponse createInventory(@RequestBody InventoryCreateRequest request);

    default InventoryCreateResponse fallbackCreateInventory(InventoryCreateRequest request, Throwable throwable) {
        log.error("Inventory service unavailable for inventory creation - productId: {}",
                request.productId(), throwable);

        return new InventoryCreateResponse(
                request.productId(),
                request.warehouseId(),
                false,
                "Service unavailable - scheduled for retry"
        );
    }

}
