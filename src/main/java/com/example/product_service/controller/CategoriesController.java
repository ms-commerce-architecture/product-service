package com.example.product_service.controller;


import com.example.product_service.dto.request.CategoriesRequest;
import com.example.product_service.dto.response.CategoriesResponse;
import com.example.product_service.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {

  private final CategoriesService categoriesService;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
    public CategoriesResponse create(@RequestBody CategoriesRequest categories) {
      return  categoriesService.createCategories(categories);
  }
}
