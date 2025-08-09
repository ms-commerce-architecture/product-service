package com.example.product_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(value = "product_attributes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductAttributes {
    @Id
    private String attribute_id;
    private String product_id;
    private String attribute_name;
    private String attribute_value;
}
