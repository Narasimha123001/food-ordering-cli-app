package com.JavaProject.foodiecliapp.ui;

import com.JavaProject.foodiecliapp.controller.CustomerController;
import com.JavaProject.foodiecliapp.exceptions.CustomerAlreadyExitsException;
import com.JavaProject.foodiecliapp.exceptions.CustomerNotFoundException;
import com.JavaProject.foodiecliapp.model.Customer;



import java.util.List;
import java.util.Scanner;

import static com.JavaProject.foodiecliapp.util.Factory.customerController;

public class CustomerMenu extends Menu{

    private final CustomerController customerController;

    public CustomerMenu() {
        this.customerController = customerController();
    }

    @Override
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO CUSTOMER SECTION");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Register (New Customer)");
                System.out.println("2. Login  (Existing Customer)");
                System.out.println("3. Search Customer");
                System.out.println("4. Display All Customers ");
                System.out.println("5. Update Customer");
                System.out.println("6. Delete Customer");
                System.out.println("7. Exit");

                System.out.println("Please enter your choice (1-7)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> customerRegisterForm();
                    case 2 -> customerLoginForm();
                    case 3 -> customerSearchForm();
                    case 4 -> displayAllCustomers();
                    case 5 -> customerUpdateForm();
                    case 6 -> deleteCustomerForm();
                    case 7 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-7)");

                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMenu();
        }
    }

    private void customerSearchForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Customer id:");
            String id = scanner.nextLine();
            Customer customerById = customerController().getCustomerById(id);
            displayCustomerDetails(customerById);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    void customerLoginForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the email");
            String email = scanner.nextLine();
            System.out.println("Enter the password");
            String password = scanner.nextLine();

            Customer existingCustomer = customerController().validateCustomerLogin(email,password);
            System.out.println("Login Successfully");
            System.out.println("Welcome Mr : "+existingCustomer.getName());
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    private void deleteCustomerForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the customer id clearly ");
            System.out.println("Enter id");
            String id = scanner.nextLine();
            customerController.deleteCustomer(id);
            System.out.println("Successfully Customer deleted");
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some internal code error , please try again");
        }

    }

    private void customerUpdateForm() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please update the following details");
            System.out.println("Enter your Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter E-mail");
            String email = scanner.nextLine();
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            Customer customer = new Customer();
            customer.setId(id)
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password);
            Customer updatedCustomer = customerController.updateCustomer(customer);
            System.out.println("Updated Successfully");
            displayCustomerDetails(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("some internal error occurred , please try again");
            customerUpdateForm();
        }
    }


    private void customerRegisterForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please register entering the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter E-mail");
            String email = scanner.nextLine();
        /*Console console = System.console();
        System.out.println("console : " + console);
        char[] passwordArray = console.readPassword("Enter Password (invisible)");
        String password = String.valueOf(passwordArray);*/
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(password);

            Customer savedCustomer = customerController.save(customer);
            System.out.println("Customer Registration Successful");
            displayCustomerDetails(savedCustomer);
        } catch (CustomerAlreadyExitsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            customerRegisterForm();
        }
    }

    private void displayCustomerDetails(Customer customer) {
        displayMenuHeader("Customer Details");
        System.out.printf("%-10s %-30s %-80s %-30s\n","Id","Name","E-mail","Password");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s\n",customer.getId(),customer.getName(),customer.getEmail(),"*".repeat(customer.getPassword().length()));
    }
    private void displayAllCustomers() {
        List<Customer> customerList = this.customerController.getAllCustomerList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println("Customer");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "E-mail", "Password");
        System.out.println(dashesLine);
        customerList.forEach(customer -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", customer.getId(), customer.getName(), customer.getEmail(), "*".repeat(customer.getPassword().length()));
        });
    }

}
