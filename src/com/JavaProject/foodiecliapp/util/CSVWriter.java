package com.JavaProject.foodiecliapp.util;

import com.JavaProject.foodiecliapp.model.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public void writeCustomerToCsv(List<Customer> customers, String customerFilePath) {
        try (FileWriter writer = new FileWriter(customerFilePath)) {
            for (Customer customer : customers) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPassword()));
            }
        } catch (IOException e) {
            System.out.println("Error occurs! while saving the customer in the file path" + customerFilePath);
            System.out.println(e.getMessage());
        }
    }
}