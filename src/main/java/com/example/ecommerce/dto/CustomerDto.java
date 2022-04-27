package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data // replace the setters and getters for the variables
public class CustomerDto {
    Long id;

    @NotNull // this variable can't be null
    @Size(min=3) // the minimum for this variable to be accepted is 3
    String name;

    String email;

    String address;

    String cardNum;

    Date DOB;
}
