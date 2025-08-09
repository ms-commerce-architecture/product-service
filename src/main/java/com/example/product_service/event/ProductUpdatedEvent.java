package com.example.product_service.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdatedEvent {
    private String productName;
    private String productCode;
    private String email;
    private String firstName;
    private String lastName;
}
