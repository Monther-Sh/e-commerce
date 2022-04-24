package com.example.ecommerce.services;

import com.example.ecommerce.dto.ProductDto;

import java.util.List;

public interface ProductsServices {
    ProductDto createProduct(ProductDto ProductDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    ProductDto updateProductById(ProductDto ProductDto, long id);

    void deleteProductById(long id);
}
