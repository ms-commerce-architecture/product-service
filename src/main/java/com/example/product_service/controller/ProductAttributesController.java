package com.example.product_service.controller;


import com.example.product_service.dto.request.AttributesRequest;
import com.example.product_service.dto.response.AttributesResponse;
import com.example.product_service.service.ProductAttributesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attributes")
@RequiredArgsConstructor
public class ProductAttributesController {
    private final ProductAttributesServices productAttributesServices;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttributesResponse createProductAttributes(@RequestBody AttributesRequest productAttributes) {
          return productAttributesServices.createAttributes(productAttributes);
    }
}
