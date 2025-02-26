package com.JavaProject.foodiecliapp.repository;

import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class DishRepository {

    private final List<Dish> dishList;

    public DishRepository() {
        this.dishList = Factory.csvReader().readDishesFromCsv();
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
                    dish.setName(dishToBeUpdated.getName())
                            .setPrice(dishToBeUpdated.getPrice())
                            .setDescription(dishToBeUpdated.getDescription());

                    return dish;
                });
        return optionalDish.orElse(null);
    }

    public void deleteDish(Dish dish){
        this.dishList.remove(dish);

    }
}
