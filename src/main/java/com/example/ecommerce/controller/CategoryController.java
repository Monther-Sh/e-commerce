package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.exceptions.BadRequest;
import com.example.ecommerce.services.CategoryServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // to identify this class as a controller for this api
@RequestMapping("/e-commerce/category") // the path for calling the api
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices){
        this.categoryServices=categoryServices;
    }

    @GetMapping // get the information from the database
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok().body(categoryServices.getAllCategories());
    }
    @GetMapping("{id}") // to specify that there will be an id after the path
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name="id") long id){
        return ResponseEntity.ok().body(categoryServices.getCategoryById(id));
    }

    @PostMapping // for creating a new data
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
       if (categoryDto.getId() != null){
           log.error("Cannot have an ID {}" , categoryDto);
           throw new BadRequest("you cant send an id with the request");
       }
       return  new ResponseEntity(categoryServices.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}") // updating data
    public ResponseEntity<CategoryDto> updateCategory(@Valid @ RequestBody CategoryDto categoryDto, @PathVariable(name="id") long id){
        return new ResponseEntity(categoryServices.updateCategory(categoryDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}") // deleting data
    public ResponseEntity<String> deleteCategory(@PathVariable(name="id") long id){
        categoryServices.deleteCategoryById(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
