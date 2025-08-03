package com.example.product_service.dto.request;

import java.math.BigInteger;

public record AttributesRequest(
         BigInteger product_id,
         String attribute_name,
         String attribute_value) {
}
