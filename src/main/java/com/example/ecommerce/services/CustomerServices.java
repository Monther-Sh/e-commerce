package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerDto;

import java.util.List;

public interface CustomerServices {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto CustomerDto,long id);

    void deleteCustomerById(long id);
}
