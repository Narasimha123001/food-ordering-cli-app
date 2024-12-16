package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
import com.JavaProject.foodiecliapp.model.Dish;

import java.util.List;

public interface DishService {

    public Dish save(Dish dish) throws DishAlreadyExistsException;

    public  List<Dish> getDishList();

    public Dish updateDish(Dish dishToBeUpdated) throws DishesNotFoundException;

    public Dish getDishById(String id) throws DishesNotFoundException;

    public void deleteDish(String id) throws DishesNotFoundException;
}
