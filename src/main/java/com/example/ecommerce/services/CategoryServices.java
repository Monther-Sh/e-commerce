package com.example.ecommerce.services;

import com.example.ecommerce.dto.CategoryDto;

import java.util.List;
// this page is used for determining what methods the api will do
public interface CategoryServices {
    CategoryDto createCategory(CategoryDto CategoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto categoryDto,long id);

    void deleteCategoryById(long id);
}
