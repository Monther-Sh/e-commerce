package com.example.ecommerce.services.imp;

import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exceptions.ApiRequestExeption;
import com.example.ecommerce.reprositry.CustomerRepository;
import com.example.ecommerce.services.CustomerServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service // to let the api know that this page is responsible for services
public class CustomerServicesImp implements CustomerServices {
    private CustomerRepository customerRepository;

    public CustomerServicesImp(CustomerRepository customerRepository){this.customerRepository=customerRepository;}
    @Override
    public CustomerDto createCustomer(CustomerDto CustomerDto) {

        Customer customer = mapToEntity(CustomerDto);
        Customer newCustomer = customerRepository.save(customer);

        CustomerDto customerDto = mapToDTO(newCustomer);
        return customerDto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer1 -> mapToDTO(customer1)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->  new ApiRequestExeption("Couldn't find the customer"));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the customer"));
        customer.setName(CustomerDto.getName());
        customer.setEmail(CustomerDto.getEmail());
        customer.setAddress(CustomerDto.getAddress());
        customer.setCardNum(CustomerDto.getCardNum());
        customer.setDOB(CustomerDto.getDOB());

        Customer newCustomer = customerRepository.save(customer);
        return mapToDTO(newCustomer);
    }

    @Override
    public void deleteCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the customer"));
        customerRepository.delete(customer);
    }
    // convert the data from an entity to DTO
    private CustomerDto mapToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setEmail(customer.getEmail());
        customerDto.setCardNum(customer.getCardNum());
        customerDto.setDOB(customer.getDOB());
        return customerDto;
    }
    // convert the data from DTO to entity
    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setCardNum(customerDto.getCardNum());
        customer.setDOB(customerDto.getDOB());
        return customer;
    }
}
