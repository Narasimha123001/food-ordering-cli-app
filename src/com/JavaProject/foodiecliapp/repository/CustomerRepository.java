package com.JavaProject.foodiecliapp.repository;

import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.util.CSVReader;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private List<Customer> customerList;

    public CustomerRepository() {
        CSVReader csvReader = new CSVReader();
        this.customerList = csvReader.readCustomersFromCsv();
    }

    public List<Customer> getAllCustomerList(){
        return customerList;
    }
        //adding new Customer
    public Customer save(Customer customer){
        this.customerList.add(customer);
        return customer;
    }

    public Optional<Customer> findCustomerById(String id){
        return this.customerList.stream()
                .filter(customer -> customer.getId().equals(id)).findFirst();
    }
 }
