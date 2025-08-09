package com.example.product_service.dto.request;



public record AttributesRequest(
         String product_id,
         String attribute_name,
         String attribute_value) {
}
