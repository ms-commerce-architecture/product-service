package com.example.product_service.dto.response;

public record AttributesResponse (String attribute_id,
                                  String product_id,
                                  String attribute_name,
                                  String attribute_value){
}
