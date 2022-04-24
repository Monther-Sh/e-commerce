package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProductDto {
    Long id;

    @NotNull
    @Size(min=3)
    String name;

    String description;


    Long purchasePrice;


    Long sellingPrice;


    Date expiryDate;


    String category;
}
