package com.JavaProject.foodiecliapp.controller;

import com.JavaProject.foodiecliapp.exceptions.OrderExistsExceptions;
import com.JavaProject.foodiecliapp.exceptions.OrderNotFoundException;
import com.JavaProject.foodiecliapp.model.Order;
import com.JavaProject.foodiecliapp.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    public Order save(Order order) throws OrderExistsExceptions{
        return this.orderService.save(order);
    }

    public List<Order> getOrderList(){
        return this.orderService.getOrderList();
    }

    public Order findOrderById(String id) throws OrderNotFoundException{
        return this.orderService.findOrderById(id);
    }
}
