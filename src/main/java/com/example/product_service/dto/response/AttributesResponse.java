package com.example.product_service.dto.response;

import java.math.BigInteger;

public record AttributesResponse (String attribute_id,
                                  BigInteger product_id,
                                  String attribute_name,
                                  String attribute_value){
}
