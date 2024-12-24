package com.JavaProject.foodiecliapp.ui;

import com.JavaProject.foodiecliapp.controller.OrderController;
import com.JavaProject.foodiecliapp.exceptions.DishesNotFoundException;
import com.JavaProject.foodiecliapp.exceptions.OrderExistsExceptions;
import com.JavaProject.foodiecliapp.exceptions.RestaurantNotFound;
import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.model.Order;
import com.JavaProject.foodiecliapp.model.Restaurant;
import com.JavaProject.foodiecliapp.service.CustomerService;
import com.JavaProject.foodiecliapp.service.DishService;
import com.JavaProject.foodiecliapp.service.RestaurantService;
import com.JavaProject.foodiecliapp.util.Factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class orderMenu extends Menu{
    private final OrderController orderController;

    public orderMenu() {
        this.orderController = Factory.orderController();
    }
    public void displayMenu(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");
            System.out.println("1. Place New Order");
            System.out.println("2. Search Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Exit");
            System.out.println("Please enter your choice (1-4)");

            int input = scanner.nextInt();

            // Handle user selection.
            switch (input) {
                case 1 -> newOrderForm(); // Option to place a new order.
                case 2 -> searchOrderForm(); // Option to search for an order.
                case 3 -> ordersList(); // Option to view all orders.
                case 4 -> {
                    System.out.println("Thank you, See you again!");
                    super.displayMenu(); // Exit to the main menu.
                }
                default -> System.out.println("Invalid Input. Please enter the valid input from (1-4)");
            }
        
            
        } catch (Exception e) {
            System.out.println("Some internal error occur.Please try after some time");
        }
    }

    private void ordersList() {
        List<Order> orderList = this.orderController.getOrderList();
        displayMenuHeader("All Order Details");

        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
        printDashLine();

        //Items
        orderList.forEach(order -> {
            String dishNames = order.getDishes().stream()
                    .map(Dish::getName)
                    .collect(Collectors.joining(","));
            System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n",
                    order.getId(),
                    order.getCustomer().getName(),
                    order.getRestaurant().getName(),
                    dishNames,
                    order.getOrderDate(),
                    order.getPrices());
        });
        System.out.println("\n\n");
    }

    private void searchOrderForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the following details to search for Order\n");
            System.out.println("Enter Id");
            String orderId = scanner.nextLine();
            Order order = this.orderController.getOrderById(orderId);
            displayOrderDetails(order);
        }catch (Exception e){
            System.out.println(e.getMessage());
            displayMenu();
        }

    }

    private void displayOrderDetails(Order order){
        String dishNames = order.getDishes().stream()
                        .map(Dish::getName)
                                .collect(Collectors.joining(","));

        displayMenuHeader("Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n",
                "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
        printDashLine();
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n",
                    order.getId(),
                    order.getCustomer().getName(),
                    order.getRestaurant().getName(),
                    dishNames,
                    order.getOrderDate(),
                    String.format("$%.2f",order.getPrices()));
    }



    private void newOrderForm() throws DishesNotFoundException {

        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.customerService();
            RestaurantService restaurantService = Factory.restaurantService();
            DishService dishService = Factory.dishService();
            loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            if(loggedInCustomer != null )
                System.out.println("Hello , " + loggedInCustomer.getName());
            while (loggedInCustomer == null) {
                System.out.println("Please login to place an order");
                new CustomerMenu().customerLoginForm();
                loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            }
            System.out.println("Enter Order Id :");
            String id = scanner.nextLine();

            while (restaurant == null) {
                new RestaurantsMenu().displayRestaurants();
                printDashLine();
                System.out.println("Choose the Restaurant Id (Ex: R08 )");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
            }
            char addMoreItems = 'Y';
            while (addMoreItems == 'Y') {
                new RestaurantsMenu().displayMenuItems(restaurant.getId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex : D001 )");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("One Dish is added successfully : " + selectedDish.getName());
                System.out.println("Do you want to add more dishes (Y/N)");
                addMoreItems = scanner.nextLine().charAt(0);
            }


            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();



            Order order = new Order();
            order.setId(id);
                    order.setCustomer(loggedInCustomer);
                    order.setRestaurant(restaurant);
                    order.setDishes(dishList);
                    order.setPrices(orderPrice);
                    order.setOrderDate(orderDate);



            Order placedOrder = orderController.save(order);
            if(placedOrder != null)
                System.out.println("Order Placed Successfully with the following details");
            displayOrderDetails(placedOrder);


        } catch (RestaurantNotFound | OrderExistsExceptions e) {
            System.out.println(e.getMessage());;
        }
    }
    private double calculateOrderTotalPrice(List<Dish> dishList) {
        return dishList.stream().mapToDouble(Dish::getPrice).sum();
    }
}
    


