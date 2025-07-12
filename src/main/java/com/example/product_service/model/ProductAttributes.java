package com.example.product_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product_attributes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductAttributes {
}
