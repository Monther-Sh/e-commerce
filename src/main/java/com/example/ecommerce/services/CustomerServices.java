package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerDto;

import java.util.List;
// this page is used for determining what methods the api will do
public interface CustomerServices {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto CustomerDto,long id);

    void deleteCustomerById(long id);
}
