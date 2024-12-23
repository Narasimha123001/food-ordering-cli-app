package com.JavaProject.foodiecliapp.repository;

import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private final List<Customer> customerList;
   // private final String customerFilePath ="C:\\Users\\naras\\OneDrive\\Desktop\\Full stack java\\JavaWorkSpace\\food-ordering-cli-app\\data\\customers.csv";

    public CustomerRepository() {
        this.customerList = Factory.csvReader().readCustomersFromCsv();
    }

    public List<Customer> getCustomersList(){
        return this.customerList;
    }
        //adding new Customer
    public Customer save(Customer customer){
        this.customerList.add(customer);
        //Factory.getCsvWriter().writeCustomerToCsv(customerList ,customerFilePath );
        return customer;
    }

    public Optional<Customer> findCustomerById(String id){
        return this.customerList.stream()
                .filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public Customer updateCustomer(Customer customerToBeUpdate){
        Optional<Customer> optionalCustomer = this.customerList.stream()
                .filter(customer -> customer.getId().equals(customerToBeUpdate.getId()))
                .findFirst()
                .map(customer -> {
                    customer.setName(customerToBeUpdate.getName())
                            .setEmail(customerToBeUpdate.getEmail())
                            .setPassword(customerToBeUpdate.getPassword());
                    //Factory.getCsvWriter().writeCustomerToCsv(customerList, customerFilePath);
                    return customer;
                });
        return optionalCustomer.orElse(null);
    }

    public void deleteCustomer(Customer customer){
         this.customerList.remove(customer);
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password){
        return this.customerList.stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password)).findFirst();
    }
}
