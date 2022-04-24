package com.example.ecommerce.services.imp;

import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Products;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.reprositry.ProductRepository;
import com.example.ecommerce.services.ProductsServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductsServicesImp implements ProductsServices {
    private ProductRepository productRepository;
    private CategoryServiceImp categoryServiceImp;
    public ProductsServicesImp(ProductRepository productRepository){this.productRepository=productRepository;}
    @Override
    public ProductDto createProduct(ProductDto ProductDto) {

            Products products = mapToEntity(ProductDto);
            Products newProduct = productRepository.save(products);

            ProductDto productDto = mapToDTO(newProduct);
            return productDto;

    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products.stream().map(products1 -> mapToDTO(products1)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        Products products = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(products);
    }

    @Override
    public ProductDto updateProductById(ProductDto ProductDto, long id) {
        Products products = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            products.setName(ProductDto.getName());
            products.setDescription(ProductDto.getDescription());
            products.setPurchasePrice(ProductDto.getPurchasePrice());
            products.setSellingPrice(ProductDto.getSellingPrice());
            products.setExpiryDate(ProductDto.getExpiryDate());
            products.setCategory(ProductDto.getCategory());

            Products updatedProduct = productRepository.save(products);
            return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(long id) {
        Products products = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(products);
    }

    private ProductDto mapToDTO(Products products){
        ProductDto productDto = new ProductDto();
        productDto.setId(products.getId());
        productDto.setName(products.getName());
        productDto.setDescription(products.getDescription());
        productDto.setPurchasePrice(products.getPurchasePrice());
        productDto.setSellingPrice(products.getSellingPrice());
        productDto.setExpiryDate(products.getExpiryDate());
        productDto.setCategory(products.getCategory());
        return productDto;
    }

    private Products mapToEntity(ProductDto productDto){
        Products products = new Products();
        products.setName(productDto.getName());
        products.setDescription(productDto.getDescription());
        products.setPurchasePrice(productDto.getPurchasePrice());
        products.setSellingPrice(productDto.getSellingPrice());
        products.setExpiryDate(productDto.getExpiryDate());
        products.setCategory(productDto.getCategory());
        return products;
    }
}
