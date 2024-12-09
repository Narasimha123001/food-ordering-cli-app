package com.JavaProject.foodiecliapp.util;

import com.JavaProject.foodiecliapp.controller.CustomerController;
import com.JavaProject.foodiecliapp.repository.CustomerRepository;
import com.JavaProject.foodiecliapp.service.CustomerServiceImpl;

public class Factory {

    public static CustomerRepository customerRepository(){
        return new CustomerRepository();
    }

    public static CustomerServiceImpl customerService(){
        return new CustomerServiceImpl(customerRepository());
    }

    public static CustomerController customerController(){
        return new CustomerController(customerService());
    }
}
