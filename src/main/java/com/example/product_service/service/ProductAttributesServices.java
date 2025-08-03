package com.example.product_service.service;


import com.example.product_service.dto.request.AttributesRequest;
import com.example.product_service.dto.response.AttributesResponse;
import com.example.product_service.model.ProductAttributes;
import com.example.product_service.repository.ProductAttributesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductAttributesServices {
    private final ProductAttributesRepository productAttributesRepository;


    public AttributesResponse createAttributes(AttributesRequest attributesRequest) {
        ProductAttributes productAttributes = ProductAttributes.builder().product_id(attributesRequest.product_id())
                .attribute_name(attributesRequest.attribute_name()).attribute_value(attributesRequest.attribute_value())
                .build();
         ProductAttributes savedProductAttributes = productAttributesRepository.save(productAttributes);

         return new AttributesResponse(savedProductAttributes.getAttribute_id(),savedProductAttributes.getProduct_id(),
                 savedProductAttributes.getAttribute_name(), savedProductAttributes.getAttribute_value());

    }


}
