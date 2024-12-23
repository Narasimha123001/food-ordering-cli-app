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

    private static final class Holder {
        private static final CSVReader CSV_READER = new CSVReader();
        private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();
        private static final CustomerServiceImpl CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY);
        private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController(CUSTOMER_SERVICE);
        private static final DishRepository DISH_REPOSITORY = new DishRepository();
        private static final DishServiceImpl DISH_SERVICE = new DishServiceImpl(DISH_REPOSITORY);
        private static final DishController DISH_CONTROLLER = new DishController(DISH_SERVICE);
        private static final RestaurantRepository RESTAURANT_REPOSITORY = new RestaurantRepository();
        private static final RestaurantServiceImpl RESTAURANT_SERVICE = new RestaurantServiceImpl(RESTAURANT_REPOSITORY);
        private static final RestaurantController RESTAURANT_CONTROLLER = new RestaurantController(RESTAURANT_SERVICE);
        private static final OrderRepository ORDER_REPOSITORY = new OrderRepository();
        private static final OrderServiceImpl ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY);
        private static final OrderController ORDER_CONTROLLER = new OrderController(ORDER_SERVICE);
    }

    // Static method access provides the same functionality with reduced boilerplate
    public static CSVReader csvReader() {
        return Holder.CSV_READER;
    }

    public static CustomerRepository customerRepository() {
        return Holder.CUSTOMER_REPOSITORY;
    }

    public static CustomerServiceImpl customerService() {
        return Holder.CUSTOMER_SERVICE;
    }

    public static CustomerController customerController() {
        return Holder.CUSTOMER_CONTROLLER;
    }

    public static DishRepository dishRepository() {
        return Holder.DISH_REPOSITORY;
    }

    public static DishServiceImpl dishService() {
        return Holder.DISH_SERVICE;
    }

    public static DishController dishController() {
        return Holder.DISH_CONTROLLER;
    }

    public static RestaurantRepository restaurantRepository() {
        return Holder.RESTAURANT_REPOSITORY;
    }

    public static RestaurantServiceImpl restaurantService() {
        return Holder.RESTAURANT_SERVICE;
    }

    public static RestaurantController restaurantController() {
        return Holder.RESTAURANT_CONTROLLER;
    }

    public static OrderRepository orderRepository() {
        return Holder.ORDER_REPOSITORY;
    }

    public static OrderServiceImpl orderService() {
        return Holder.ORDER_SERVICE;
    }

    public static OrderController orderController() {
        return Holder.ORDER_CONTROLLER;
    }
}