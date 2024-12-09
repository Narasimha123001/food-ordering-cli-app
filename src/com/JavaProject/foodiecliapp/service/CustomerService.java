package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.CustomerExitsException;
import com.JavaProject.foodiecliapp.model.Customer;

public interface CustomerService {

    public Customer save(Customer customer) throws CustomerExitsException;

}
