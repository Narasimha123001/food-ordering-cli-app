package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }



    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(restaurant.getId());
        if(restaurantById.isPresent())
           throw  new RestaurantAlreadyExistsException("The restaurant already exist on the id:"+restaurant.getId()+" please try with another id");
    return this.restaurantRepository.saveRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.getRestaurantList();
    }
/*
    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFound {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(id);
        if(restaurantById.isEmpty())
            throw new RestaurantNotFound("The restaurant is not based on the id:"+id+" please try again with different id");
        return this.getRestaurantById(id);
    }

 */
}
