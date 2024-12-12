package com.JavaProject.foodiecliapp.repository;

import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.util.CSVReader;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {

    private final List<Restaurant> restaurantList;

    public RestaurantRepository() {
        CSVReader csvReader = new CSVReader();
        this.restaurantList = csvReader.readRestaurantsFromCsv();
    }

    public List<Restaurant> getRestaurantList() {
        return this.restaurantList;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        this.restaurantList.add(restaurant);
        return restaurant;
    }

    public Optional<Restaurant> findRestaurantById(String id) {
        return this.restaurantList.stream()
                .filter(restaurant -> restaurant.getId().equals(id)).findFirst();
    }

    public Restaurant updateRestaurantDetails(Restaurant restaurantToBeUpdated){
        Optional<Restaurant> restaurantOptional = this.restaurantList.stream().filter(restaurant -> restaurant.getId().equals(restaurantToBeUpdated.getId()))
                .findFirst()
                .map(restaurant -> {
                    restaurant.setId(restaurantToBeUpdated.getId());
                    restaurant.setName(restaurantToBeUpdated.getName());
                    restaurant.setAddress(restaurantToBeUpdated.getAddress());
                    restaurant.setMenu(restaurantToBeUpdated.getMenu());
                    return restaurant;
                });
        return restaurantOptional.orElse(null);
    }
    public void deleteRestaurant(Restaurant restaurant){
        this.restaurantList.remove(restaurant);
    }
}
