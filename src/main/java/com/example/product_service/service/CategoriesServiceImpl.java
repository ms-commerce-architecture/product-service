package com.example.product_service.service;


import com.example.product_service.dto.request.CategoriesRequest;
import com.example.product_service.dto.response.CategoriesResponse;
import com.example.product_service.exceptions.ResourceNotFoundException;
import com.example.product_service.model.Categories;
import com.example.product_service.repository.CategoriesRopository;
import com.example.product_service.service.interfaces.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRopository categoriesRepository;

    @Override
    public CategoriesResponse createCategories(CategoriesRequest categoriesRequest) {
        Categories categories = Categories.builder()
                .categoryName(categoriesRequest.categoryName())
                .description(categoriesRequest.description())
                .parentCategoryId(categoriesRequest.parentCategoryId())
                .status(categoriesRequest.status())
                .build();

        Categories saved = categoriesRepository.save(categories);

        return new CategoriesResponse(
                saved.getCategoryId(),
                saved.getCategoryName(),
                saved.getParentCategoryId(),
                saved.getDescription(),
                saved.getStatus()
        );
    }

    @Override
    public CategoriesResponse updateCategory(String categoryId, CategoriesRequest request) {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));

        category.setCategoryName(request.categoryName());
        category.setDescription(request.description());
        category.setParentCategoryId(request.parentCategoryId());
        category.setStatus(request.status());

        Categories updated = categoriesRepository.save(category);
        return new CategoriesResponse(
                updated.getCategoryId(),
                updated.getCategoryName(),
                updated.getParentCategoryId(),
                updated.getDescription(),
                updated.getStatus()
        );
    }

    @Override
    public void deleteCategory(String categoryId) {
        validateCategoryExists(categoryId);
        categoriesRepository.deleteById(categoryId);
    }

    @Override
    public CategoriesResponse getCategoryById(String categoryId) {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));

        return new CategoriesResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getParentCategoryId(),
                category.getDescription(),
                category.getStatus()
        );
    }

    @Override
    public List<CategoriesResponse> getAllCategories() {
        return categoriesRepository.findAll()
                .stream()
                .map(c -> new CategoriesResponse(
                        c.getCategoryId(),
                        c.getCategoryName(),
                        c.getParentCategoryId(),
                        c.getDescription(),
                        c.getStatus()
                ))
                .toList();
    }

    @Override
    public void validateCategoryExists(String categoryId) {
        if (!categoriesRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category ID not found: " + categoryId);
        }
    }
}