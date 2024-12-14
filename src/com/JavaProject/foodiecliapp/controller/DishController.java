package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
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

}
