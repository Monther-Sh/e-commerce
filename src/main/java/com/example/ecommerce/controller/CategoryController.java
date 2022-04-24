package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.exceptions.BadRequestException;
import com.example.ecommerce.services.CategoryServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/e-commerce/category")
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices){
        this.categoryServices=categoryServices;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok().body(categoryServices.getAllCategories());
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name="id") long id){
        return ResponseEntity.ok().body(categoryServices.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
       if (categoryDto.getId() != null){
           log.error("Cannot have an ID {}" , categoryDto);
           throw new BadRequestException(CategoryController.class.getSimpleName(),"Id");
       }
       return  new ResponseEntity(categoryServices.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @ RequestBody CategoryDto categoryDto, @PathVariable(name="id") long id){
        return new ResponseEntity(categoryServices.updateCategory(categoryDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name="id") long id){
        categoryServices.deleteCategoryById(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
