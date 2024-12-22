package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.CustomerAlreadyExitsException;
import com.JavaProject.foodiecliapp.exceptions.CustomerNotFoundException;
import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private Customer currentLoggedInCustomer;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerAlreadyExitsException {
        Optional<Customer> customerById=this.customerRepository.findCustomerById(customer.getId());
        if(customerById.isPresent())
            throw new CustomerAlreadyExitsException("Id is already present: "+customer.getId()+" please try with different id ");

        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerList() {
        return this.customerRepository.getCustomersList();
    }
    public Customer getCustomerById(String id)throws CustomerNotFoundException{
        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerById(id);
        if(optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Customer not present with this: "+id+ "please try with correct id! fool");
        return optionalCustomer.get();
    }

    @Override
    public Customer updateCustomer(Customer customerToBeUpdate) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerById(customerToBeUpdate.getId());
        if(optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Customer Not Found with Id: "+customerToBeUpdate.getId());
        return this.customerRepository.updateCustomer(customerToBeUpdate);
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerById(id);
        if(optionalCustomer.isEmpty())
            throw  new CustomerNotFoundException("Customer not found with id:" +id);
        this.customerRepository.deleteCustomer(getCustomerById(id));
    }

    @Override
    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = this.customerRepository.findByEmailAndPassword(email,password);
        if(optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Invalid email and Password");
        return optionalCustomer.get();
    }

    @Override
    public void setCurrentLoggedInCustomer(Customer customer) {
        this.currentLoggedInCustomer = customer;
    }

    @Override
    public Customer getLoggedCustomer() {
        return currentLoggedInCustomer;
    }
}
