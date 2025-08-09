package com.example.product_service.controller;


import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;

import com.example.product_service.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest product) {
        log.info("Create @! product : {}", product);
     return  productService.createProduct(product);
    }



    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        log.info("Get product by id: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String categoryId) {
        log.info("Get all products - page: {}, size: {}, category: {}", page, size, categoryId);
        return productService.getAllProducts(page, size, categoryId);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest productRequest) {
        log.info("Update product with id: {}", id);
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        log.info("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }




    @GetMapping("/search")
    public Page<ProductResponse> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Search products with query: {}", query);
        return productService.searchProducts(query, page, size);
    }
}
