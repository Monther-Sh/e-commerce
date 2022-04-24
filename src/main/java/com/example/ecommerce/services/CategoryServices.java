package com.example.ecommerce.services;

import com.example.ecommerce.dto.CategoryDto;

import java.util.List;

public interface CategoryServices {
    CategoryDto createCategory(CategoryDto CategoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto categoryDto,long id);

    Boolean isCategoryFound(String name);

    void deleteCategoryById(long id);
}
