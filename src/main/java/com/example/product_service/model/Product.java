package com.example.product_service.model;


import com.example.product_service.model.Enums.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String productName;
    private String productCode;
    private String description;
    private String categoryId;
    private BigDecimal price;
    private BigDecimal costPrice;
    private BigDecimal weight;
    private String dimensions;
    private ProductStatus status;

}
