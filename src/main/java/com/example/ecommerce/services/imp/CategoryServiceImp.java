package com.example.ecommerce.services.imp;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.reprositry.CategoryRepository;
import com.example.ecommerce.services.CategoryServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
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
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category newCategory = categoryRepository.save(category);
        return mapToDTO(newCategory);
    }

    @Override
    public Boolean isCategoryFound(String name) {
        List<Category> category = categoryRepository.findAll();
        for (int i = 0; i < category.size(); i++) {
            if (category.get(i).equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }

    private CategoryDto mapToDTO(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
