package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.repository.RestaurantRepository;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
       Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findRestaurantById(restaurant.getId());
       if(optionalRestaurant.isPresent())
           throw new RestaurantAlreadyExistsException("Restaurant already present on same Id. Please try again with another id");
       return this.restaurantRepository.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFound {
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findRestaurantById(id);
        if(optionalRestaurant.isEmpty())
            throw  new RestaurantNotFound("Restaurant not found by this id:"+id);
        return optionalRestaurant.get();
    }

    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.getRestaurantList();
    }

    @Override
    public Restaurant updateRestaurantDetails(Restaurant restaurant) throws  RestaurantNotFound {
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findRestaurantById(restaurant.getId());
        if(optionalRestaurant.isEmpty())
            throw new RestaurantNotFound("Restaurant Not found with this id :"+restaurant.getId());
        return restaurantRepository.updateRestaurantDetails(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) throws RestaurantNotFound {
        Optional<Restaurant> restaurantOptional = this .restaurantRepository.findRestaurantById(id);
        if(restaurantOptional.isEmpty())
            throw new RestaurantNotFound("Restaurant Not found by id"+id);
        this.restaurantRepository.deleteRestaurant(getRestaurantById(id));
    }

    @Override
    public List<Dish> getDishItems(String id) throws RestaurantNotFound, DishesNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(id);
        if(restaurantById.isEmpty())
            throw new RestaurantNotFound("Restaurant Not Found with this id:"+id);
        List<Dish> dishList = new ArrayList<>();
        Restaurant restaurant = restaurantById.get();
        List<String> dishIds = restaurant.getMenu();
        DishService dishService = Factory.dishService();
        for(String dishId : dishIds){
            Dish dish = dishService.getDishById(dishId);
            dishList.add(dish);
        }
        return dishList;
    }

}
