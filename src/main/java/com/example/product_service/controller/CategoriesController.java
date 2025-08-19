package com.example.product_service.controller;


import com.example.product_service.dto.request.CategoriesRequest;
import com.example.product_service.dto.response.CategoriesResponse;

import com.example.product_service.service.interfaces.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {

  private final CategoriesService categoriesService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoriesResponse create(@RequestBody CategoriesRequest request) {
    return categoriesService.createCategories(request);
  }

  @PutMapping("/{id}")
  public CategoriesResponse update(@PathVariable("id") String categoryId,
                                   @RequestBody CategoriesRequest request) {
    return categoriesService.updateCategory(categoryId, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String categoryId) {
    categoriesService.deleteCategory(categoryId);
  }

  @GetMapping("/{id}")
  public CategoriesResponse getById(@PathVariable("id") String categoryId) {
    return categoriesService.getCategoryById(categoryId);
  }

  @GetMapping
  public List<CategoriesResponse> getAll() {
    return categoriesService.getAllCategories();
  }
}
