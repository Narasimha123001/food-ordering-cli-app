package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.repository.DishRepository;

import java.util.List;
import java.util.Optional;

public class DishServiceImpl implements DishService {


    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish save(Dish dish) throws DishAlreadyExistsException {
        Optional<Dish> optionalDish = this.dishRepository.getDishById(dish.getId());

        if (optionalDish.isPresent())
            throw new DishAlreadyExistsException("The dish is already is present on this id"+dish.getId()+" Please try again with another id");

        return this.dishRepository.saveDish(dish);
    }

    @Override
    public List<Dish> getDishList() {
        return this.dishRepository.getDishList();
    }


}
