package com.JavaProject.foodiecliapp.service;

import com.JavaProject.foodiecliapp.exceptions.OrderExistsExceptions;
import com.JavaProject.foodiecliapp.exceptions.OrderNotFoundException;
import com.JavaProject.foodiecliapp.model.Order;
import com.JavaProject.foodiecliapp.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order save(Order order) throws OrderExistsExceptions {
        Optional<Order> orderOptional = this.orderRepository.findOrderById(order.getId());
        if(orderOptional.isPresent())
            throw new OrderExistsExceptions("Order is already exits with this id:"+order.getId());
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderList() {
        return this.orderRepository.getOrderList();
    }

    @Override
    public Order findOrderById(String id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = this.orderRepository.findOrderById(id);
        if(optionalOrder.isEmpty())
            throw new OrderNotFoundException("Order not Found by this Id:"+id);
        return optionalOrder.get();
    }
}
