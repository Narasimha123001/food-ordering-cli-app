package com.JavaProject.foodiecliapp.ui;

import com.JavaProject.foodiecliapp.controller.RestaurantController;
import com.JavaProject.foodiecliapp.exceptions.RestaurantAlreadyExistsException;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.util.Factory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RestaurantsMenu extends Menu {

    private final RestaurantController restaurantController;

    public RestaurantsMenu() {
        this.restaurantController = Factory.restaurantController();
    }

    @Override
    public void displayMenu() {
        try{
            Scanner scanner = new Scanner(System.in);
            while(true){
                displayMenuHeader("Welcome to Restaurant Menu");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Add New Restaurant");
                System.out.println("2. View All Restaurants");
                System.out.println("3. Search Restaurant");
                System.out.println("4. Update Restaurant ");
                System.out.println("5. Delete Restaurant");
                System.out.println("6. Exit");

                System.out.println("Please enter your choice (1-6)");

                int input = scanner.nextInt();
                switch (input) {
                   case 1 -> newRestaurantForm();
                    case 2 -> displayRestaurants();
                    case 3 -> restaurantSearchForm();
                    case 4 -> restaurantUpdateForm();
                    case 5 -> restaurantDeleteForm();
                    case 6 ->{
                        System.out.println("thank you see you again!");
                        super.displayMenu();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("some internal code occurs");
        }
    }

    private void restaurantDeleteForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Restaurant Id");
            String id = scanner.nextLine();
            Factory.restaurantController().deleteRestaurant(id);
            System.out.println("Restaurant delete successful");
            restaurantController.getRestaurantList();
        } catch (RestaurantNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    private void restaurantUpdateForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please the following details correctly");
            System.out.println("Restaurant Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Address");
            String address = scanner.nextLine();
            System.out.println("Enter Dishes for Menu separated by : (D010:D009)");
            String menu = scanner.nextLine();

            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurant.setMenu(Arrays.asList(menu.split(":")));
            Restaurant restaurant1 = Factory.restaurantService().updateRestaurantDetails(restaurant);
            displayRestaurant(restaurant1);
        } catch (RestaurantNotFound e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some internal error occurred.Please try again");
            restaurantUpdateForm();
        }
    }

    private void restaurantSearchForm() {

        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Restaurant id");
            String id = scanner.nextLine();
            Restaurant restaurant = Factory.restaurantController().getRestaurantById(id);
            displayRestaurant(restaurant);
        } catch (RestaurantNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    void displayRestaurants() {
        List<Restaurant> restaurantList = Factory.restaurantService().getRestaurantList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println("Restaurant");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "Address", "Menu Items");
        System.out.println(dashesLine);
        restaurantList.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));
        });
    }

    private void newRestaurantForm() {
        try{
            System.out.println("Please enter the Restaurant details");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Address");
            String address = scanner.nextLine();
            System.out.println("Enter Dishes for Menu separated by : (D010:D009)");
            String menu = scanner.nextLine();

            Restaurant savedRestaurant = new Restaurant();
            savedRestaurant.setName(name)
                    .setId(id)
                    .setAddress(address)
                    .setMenu(Arrays.asList(menu.split(":")));
            Restaurant restaurant = Factory.restaurantController().save(savedRestaurant);
            displayRestaurant(restaurant);


        } catch (RestaurantAlreadyExistsException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("some internal code error occurred! please try again");
        }
    }
    public void displayRestaurant(Restaurant restaurant) {
        displayMenuHeader("Restaurant Details");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "Address", "Menu Items");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));

    }
}

