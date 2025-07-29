package com.example.product_service.model;


import com.example.product_service.model.Enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(value = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categories {
    @Id
    private String categoryId;
    private String categoryName;
    private String parentCategoryId;
    private String description;
    private CategoryStatus status;
}
