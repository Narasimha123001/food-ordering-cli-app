package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.service.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException, RestaurantNotFound {
        return this.restaurantService.saveRestaurant(restaurant);
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantService.getRestaurantList();
    }
    public Restaurant updateRestaurantDetails(Restaurant restaurant) throws RestaurantNotFound {
        return this.restaurantService.updateRestaurantDetails(restaurant);
    }
    public Restaurant getRestaurantById(String id) throws RestaurantNotFound {
        return this.restaurantService.getRestaurantById(id);
    }
    public void deleteRestaurant(String id) throws RestaurantNotFound {
        this.restaurantService.deleteRestaurant(id);
    }
}
