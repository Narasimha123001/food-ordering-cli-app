package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.CustomerAlreadyExitsException;
import com.JavaProject.foodiecliapp.exceptions.CustomerNotFoundException;
import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerAlreadyExitsException {
        return this.customerService.save(customer);
    }

    public List<Customer> getAllCustomerList() {
        return this.customerService.getAllCustomerList();
    }
    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        return this.customerService.getCustomerById(id);
    }
    public Customer updateCustomer(Customer customerToBeUpdate) throws CustomerNotFoundException{
        return this.customerService.updateCustomer(customerToBeUpdate);
    }
    public void deleteCustomer(String id) throws CustomerNotFoundException{
        this.customerService.deleteCustomer(id);
    }
    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException{
        Customer customer = this.customerService.validateCustomerLogin(email, password);
        if( customer != null)
            this.customerService.setCurrentLoggedInCustomer(customer);
        return customer;
    }
    public Customer currentLoggedInCustomer(){
        return this.customerService.getCurrentLoggedInCustomer();
    }
}
