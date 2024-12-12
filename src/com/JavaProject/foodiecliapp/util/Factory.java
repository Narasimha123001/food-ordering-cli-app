package com.JavaProject.foodiecliapp.util;

import com.JavaProject.foodiecliapp.controller.CustomerController;
import com.JavaProject.foodiecliapp.controller.RestaurantController;
import com.JavaProject.foodiecliapp.repository.CustomerRepository;
import com.JavaProject.foodiecliapp.repository.RestaurantRepository;
import com.JavaProject.foodiecliapp.service.CustomerServiceImpl;
import com.JavaProject.foodiecliapp.service.RestaurantServiceImpl;

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

    public static RestaurantRepository restaurantRepository(){
        return new RestaurantRepository();
    }

    public static RestaurantServiceImpl restaurantService(){
        return new RestaurantServiceImpl(restaurantRepository());
    }

    public static RestaurantController restaurantController(){
       return new RestaurantController(restaurantService());
    }
}
