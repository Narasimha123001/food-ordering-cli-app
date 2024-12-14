package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.CustomerExitsException;
import com.JavaProject.foodiecliapp.exceptions.CustomerNotFoundException;
import com.JavaProject.foodiecliapp.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer save(Customer customer) throws CustomerExitsException;

    public List<Customer> getAllCustomerList();

    public Customer getCustomerById(String id)throws CustomerNotFoundException;

}
