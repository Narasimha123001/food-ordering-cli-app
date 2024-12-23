package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.CustomerAlreadyExitsException;
import com.JavaProject.foodiecliapp.exceptions.CustomerNotFoundException;
import com.JavaProject.foodiecliapp.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer save(Customer customer) throws CustomerAlreadyExitsException;

    public List<Customer> getAllCustomerList();

    public Customer getCustomerById(String id)throws CustomerNotFoundException;

    public Customer updateCustomer(Customer customerToBeUpdate) throws CustomerNotFoundException;

    public void deleteCustomer(String id) throws CustomerNotFoundException;

    public Customer validateCustomerLogin(String email , String password) throws CustomerNotFoundException;

    public void setCurrentLoggedInCustomer(Customer customer);
    public Customer getCurrentLoggedInCustomer();
}
