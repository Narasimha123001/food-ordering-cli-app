package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.OrderExistsExceptions;
import com.JavaProject.foodiecliapp.exceptions.OrderNotFoundException;
import com.JavaProject.foodiecliapp.model.Order;

import java.util.List;

public interface OrderService {
    public Order save(Order order) throws OrderExistsExceptions;

    public List<Order> getOrderList();

    public Order getOrderById(String id) throws OrderNotFoundException;
}
