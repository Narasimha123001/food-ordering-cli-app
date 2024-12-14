package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.CustomerExitsException;
import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerExitsException {
        Optional<Customer> customerById=this.customerRepository.findCustomerById(customer.getId());
        if(customerById.isPresent())
            throw new CustomerExitsException("Id is already present: "+customer.getId()+" please try with different id ");

        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerList() {
        return this.customerRepository.getAllCustomerList();
    }

}
