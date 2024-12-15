package com.JavaProject.foodiecliapp.util;

import com.JavaProject.foodiecliapp.model.Customer;
import com.JavaProject.foodiecliapp.model.Dish;
import com.JavaProject.foodiecliapp.model.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public List<Customer> readCustomersFromCsv(){

        String customerFilePath = "C:\\Users\\naras\\OneDrive\\Desktop\\Full stack java\\JavaWorkSpace\\food-ordering-cli-app\\data\\customers.csv";

        List<Customer> customerList = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(customerFilePath))){
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] data = line.split(csvSplitBy);
                //id,name,email,password
                Customer customer = new Customer();
                customer.setId(data[0])
                        .setName(data[1])
                        .setEmail(data[2])
                        .setPassword(data[3]);
                customerList.add(customer);
            }
        } catch (IOException e) {
            System.out.println("File not found in the path :"+customerFilePath);
            System.exit(0);
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public List<Dish> readDishesFromCsv(){
        String DishesFilePath ="C:\\Users\\naras\\OneDrive\\Desktop\\Full stack java\\JavaWorkSpace\\food-ordering-cli-app\\data\\dishes.csv";

        List<Dish> dishList = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(DishesFilePath))){
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(csvSplitBy);
                Dish dish = new Dish();
                dish.setId(data[0]);
                dish.setName(data[1]);
                dish.setDescription(data[2]);
                dish.setPrice(Double.parseDouble(data[3]));
                dishList.add(dish);
            }
        } catch (IOException e) {
            System.out.println("File not found in the path :"+DishesFilePath);
            System.exit(0);
            System.out.println(e.getMessage());
        }
        return  dishList;
    }

    public  List<Restaurant>  readRestaurantsFromCsv(){
        String RestaurantCsvFilePath ="C:\\Users\\naras\\OneDrive\\Desktop\\Full stack java\\JavaWorkSpace\\food-ordering-cli-app\\data\\restaurants.csv";
        List<Restaurant> restaurantList = new ArrayList<>();
        String Line;
        String CsvSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(RestaurantCsvFilePath))){
            br.readLine();
            while ((Line = br.readLine()) != null){
                String[] data = Line.split(CsvSplitBy);
                Restaurant restaurant = new Restaurant();
                restaurant.setId(data[0]);
                restaurant.setName(data[1]);
                restaurant.setAddress(data[2]);
                restaurant.setMenu(Arrays.asList(data[3].split(":")));
                restaurantList.add(restaurant);
            }

        } catch (IOException e) {
            System.out.println("File not found in this path:"+RestaurantCsvFilePath);
            System.exit(0);
            System.out.println(e.getMessage());
        }
        return restaurantList;
    }
}
