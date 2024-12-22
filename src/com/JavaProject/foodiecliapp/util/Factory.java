package com.JavaProject.foodiecliapp.util;

import com.JavaProject.foodiecliapp.controller.CustomerController;
import com.JavaProject.foodiecliapp.controller.DishController;
import com.JavaProject.foodiecliapp.controller.OrderController;
import com.JavaProject.foodiecliapp.controller.RestaurantController;
import com.JavaProject.foodiecliapp.repository.CustomerRepository;
import com.JavaProject.foodiecliapp.repository.DishRepository;
import com.JavaProject.foodiecliapp.repository.OrderRepository;
import com.JavaProject.foodiecliapp.repository.RestaurantRepository;
import com.JavaProject.foodiecliapp.service.CustomerServiceImpl;
import com.JavaProject.foodiecliapp.service.DishServiceImpl;
import com.JavaProject.foodiecliapp.service.OrderServiceImpl;
import com.JavaProject.foodiecliapp.service.RestaurantServiceImpl;

public class Factory {

    public static CSVReader getCsvReader(){
        return new CSVReader();
    }

    public static CSVWriter getCsvWriter(){
        return new CSVWriter();
    }
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

    public static DishRepository dishRepository(){
        return new DishRepository();
    }
    public static DishServiceImpl dishService(){
        return new DishServiceImpl(dishRepository());
    }
    public static DishController dishController(){
        return new DishController(dishService());
    }

    public static OrderRepository orderRepository(){
        return new OrderRepository();
    }
    public static OrderServiceImpl orderService(){
        return new OrderServiceImpl(orderRepository());
    }
    public static OrderController orderController(){
        return new OrderController(orderService());
    }

}
