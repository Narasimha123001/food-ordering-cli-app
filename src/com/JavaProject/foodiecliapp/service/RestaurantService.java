package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    //public Restaurant getRestaurantById(String id) throws RestaurantNotFound;

    public List<Restaurant>  getRestaurantList();
}