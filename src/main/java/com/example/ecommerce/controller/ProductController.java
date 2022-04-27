package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.exceptions.BadRequest;
import com.example.ecommerce.services.ProductsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController // to identify this class as a controller for this api
@RequestMapping("/e-commerce/products") // the path for calling the api
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductsServices productsServices;

    public ProductController(ProductsServices productsServices){
        this.productsServices=productsServices;
    }
    @GetMapping // get the information from the database
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productsServices.getAllProducts());
    }
    @GetMapping("{id}") // to specify that there will be an id after the path
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name="id") long id){
        return ResponseEntity.ok().body(productsServices.getProductById(id));
    }

    @PostMapping // creating data
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        if (productDto.getId() != null){
            log.error("Cannot have an ID {}" , productDto);
            throw new BadRequest("you cant send an id with the request");
        }
        return  new ResponseEntity(productsServices.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}") // updating data
    public ResponseEntity<ProductDto> updateProduct(@Valid @ RequestBody ProductDto productDto, @PathVariable(name="id") long id){
        return new ResponseEntity(productsServices.updateProductById(productDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}") // deleting data
    public ResponseEntity<String> deleteProduct(@PathVariable(name="id") long id){
        productsServices.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
