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





}
