package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data // replace the setters and getters for the variables
public class CategoryDto {
    Long id;

    @NotNull // this variable can't be null
    @Size(min=3) //specify what is the minimum length of the variable will be
    String name;

    String description;
}
