package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    Long id;

    @NotNull
    @Size(min=3)
    String name;

    String description;
}
