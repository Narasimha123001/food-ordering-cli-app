package com.JavaProject.foodiecliapp.ui;

import com.JavaProject.foodiecliapp.controller.DishController;
import com.JavaProject.foodiecliapp.exceptions.DishAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.List;
import java.util.Scanner;

public class DishesMenu extends Menu{

    private final DishController dishController;

    public DishesMenu() {
        this.dishController = Factory.dishController();
    }

    public void displayMenu() {
        try{
            Scanner scanner = new Scanner(System.in);
            while (true){
                displayMenuHeader("WELCOME TO DISHES SECTIONS");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Add New Dish");
                System.out.println("2. View All Dish Items");
                System.out.println("3. Search Dish");
                System.out.println("4. Update Dish ");
                System.out.println("5. Delete Dish");
                System.out.println("6. Exit");

                System.out.println("Please enter your choice (1-6)");


                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> newDishForm();
                    case 2 -> displayDishes();
                    case 3 -> dishSearchForm();
                    case 4 -> dishUpdateForm();
                    case 5 -> dishDeleteForm();
                    case 6 -> {
                        System.out.println("Thank you see You again!");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid input .please enter a valid input");
                }

            }

        } catch (Exception e) {
            System.out.println("Some internal error occurred !.please try again !");
            displayMenu();
        }
    }

    private void displayDishes() {
        List<Dish> dishList = this.dishController.getDishList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println("Dishes");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        System.out.println(dashesLine);
        dishList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });

    }

    private void dishDeleteForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the dishes id ");
            String id = scanner.nextLine();
            dishController.deleteDish(id);
        } catch (DishesNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some internal error Occurred !.please try again");
        }
    }

    private void dishUpdateForm() {

        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Update entering the following details\n");
            System.out.println("Enter Dish Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();

            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);

            dishController.updateDish(dish);
            displayDish(dish);
        } catch (DishesNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Some internal error occurred ! please try again");
        }
    }

    private void dishSearchForm() {
        try{
            Scanner  scanner = new Scanner(System.in);
            System.out.println("Please Enter the Dishes Id");
            String id = scanner.nextLine();
            Dish dish = this.dishController.getDishById(id);
            displayDish(dish);
        } catch (DishesNotFoundException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Some internal error occurred !.please try again ");
        }
    }

    private void newDishForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);

            Dish savedDish = this.dishController.save(dish);
            displayDish(savedDish);
        } catch (DishAlreadyExistsException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void displayDish(Dish dish) {
        displayMenuHeader("Dish Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));

    }
}
