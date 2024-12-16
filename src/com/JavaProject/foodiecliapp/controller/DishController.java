package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.service.DishServiceImpl;

import java.util.List;

public class DishController {

    private final DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    public Dish save(Dish dish) throws DishAlreadyExistsException {
        return this.dishService.save(dish);
    }
    public List<Dish> getDishList(){
        return this.dishService.getDishList();
    }

    public Dish updateDish(Dish dishToBeUpdated) throws DishesNotFoundException{
        return this.dishService.updateDish(dishToBeUpdated);
    }
    public Dish getDishById(String id) throws DishesNotFoundException{
        return this.dishService.getDishById(id);
    }
    public void deleteDish(String id) throws DishesNotFoundException{
        this.dishService.deleteDish(id);
    }
}
