package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CustomerDto {
    Long id;

    @NotNull
    @Size(min=3)
    String name;

    String email;

    String address;

    String cardNum;

    Date DOB;
}
