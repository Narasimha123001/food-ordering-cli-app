package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.CustomerExitsException;
import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.service.CustomerServiceImpl;

public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerExitsException {
        return this.customerService.save(customer);
    }

}
