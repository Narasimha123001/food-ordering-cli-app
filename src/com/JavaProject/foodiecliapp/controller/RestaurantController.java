package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.service.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return this.restaurantService.saveRestaurant(restaurant);
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantService.getRestaurantList();
    }

}
