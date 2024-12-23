package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
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

    @Override
    public Dish updateDish(Dish dishToBeUpdated) throws DishesNotFoundException {
        Optional<Dish> optionalDish = this.dishRepository.getDishById(dishToBeUpdated.getId());
        if(optionalDish.isEmpty())
            throw new DishesNotFoundException("Dish not found based on provided Id: "+dishToBeUpdated.getId()+". please try with correct id");
        return this.dishRepository.updateDish(dishToBeUpdated);
    }

    @Override
    public Dish getDishById(String id) throws DishesNotFoundException {
        Optional<Dish> optionalDish = this.dishRepository.getDishById(id);
        if(optionalDish.isEmpty())
            throw  new DishesNotFoundException("Dish not found by this id: "+id+"please try with correct id");
        return optionalDish.get();
    }

    @Override
    public void deleteDish(String id) throws DishesNotFoundException {
        Optional<Dish> optionalDish = this.dishRepository.getDishById(id);
        if(optionalDish.isEmpty())
            throw new DishesNotFoundException("Dish not found by this id: "+id+"please try with correct id");
        this.dishRepository.deleteDish(getDishById(id));
    }
}
