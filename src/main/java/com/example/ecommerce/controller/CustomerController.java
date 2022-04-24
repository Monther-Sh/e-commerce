package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.exceptions.BadRequestException;
import com.example.ecommerce.services.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/e-commerce/customer")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerServices customerServices;

    public CustomerController(CustomerServices customerServices){
        this.customerServices=customerServices;
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok().body(customerServices.getAllCustomers());
    }
    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name="id") long id){
        return ResponseEntity.ok().body(customerServices.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
        if (customerDto.getId() != null){
            log.error("Cannot have an ID {}" , customerDto);
            throw new BadRequestException(CustomerController.class.getSimpleName(),"Id");
        }
        return  new ResponseEntity(customerServices.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @ RequestBody CustomerDto customerDto, @PathVariable(name="id") long id){
        return new ResponseEntity(customerServices.updateCustomer(customerDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name="id") long id){
        customerServices.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
