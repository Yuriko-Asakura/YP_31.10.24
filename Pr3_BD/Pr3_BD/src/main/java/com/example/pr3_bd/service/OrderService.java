package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrder();
    Order createOrder (Order order);
    Order updateOrder (Order order);
    Order findOrderbyId (Long id);
    void deleteOrder (Long id);
}
