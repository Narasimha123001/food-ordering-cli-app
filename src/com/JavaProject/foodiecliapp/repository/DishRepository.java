package com.JavaProject.foodiecliapp.repository;

import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.util.CSVReader;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class DishRepository {

    private  List<Dish> dishList;

    public DishRepository() {
        this.dishList = Factory.getCsvReader().readDishesFromCsv();
    }

    public Dish saveDish(Dish dish){
        this.dishList.add(dish);
        return dish;
    }
    public  List<Dish> getDishList(){
        return this.dishList;
    }

    public Optional<Dish> getDishById(String id){
        return this.dishList.stream()
                .filter(dish -> dish.getId().equals(id)).findFirst();
    }

    public Dish updateDish(Dish dishToBeUpdated){
        Optional<Dish> optionalDish = this.dishList.stream()
                .filter(dish -> dish.getId().equals(dishToBeUpdated.getId()))
                .findFirst()
                .map(dish -> {
                    dish.setId(dishToBeUpdated.getId());
                    dish.setName(dishToBeUpdated.getName());
                    dish.setDescription(dishToBeUpdated.getDescription());
                    dish.setDescription(dishToBeUpdated.getDescription());
                    return dish;
                });
        return optionalDish.orElse(null);
    }

    public void deleteDish(Dish dish){
        this.dishList.remove(dish);
    }
}
