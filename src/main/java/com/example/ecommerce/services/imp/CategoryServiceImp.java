package com.example.ecommerce.services.imp;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exceptions.ApiRequestExeption;
import com.example.ecommerce.reprositry.CategoryRepository;
import com.example.ecommerce.services.CategoryServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service // to let the api know that this page is responsible for services
public class CategoryServiceImp implements CategoryServices {
    private CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository){this.categoryRepository=categoryRepository;}
    @Override
    public CategoryDto createCategory(CategoryDto CategoryDto) {
        Category  category = mapToEntity(CategoryDto);
        Category newCategory = categoryRepository.save(category);

        CategoryDto categoryResponse = mapToDTO(newCategory);
        return categoryResponse;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(category1 -> mapToDTO(category1)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the category"));
        return mapToDTO(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the category"));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category newCategory = categoryRepository.save(category);
        return mapToDTO(newCategory);
    }

    @Override
    public void deleteCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the category"));
        categoryRepository.delete(category);
    }
    // convert the data from an entity to DTO
    private CategoryDto mapToDTO(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
    // convert the data from DTO to entity
    private Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
