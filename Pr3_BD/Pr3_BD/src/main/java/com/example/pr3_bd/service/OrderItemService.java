package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllOrderItemr();
    OrderItem createOrderItem (OrderItem orderItem);
    OrderItem updateOrderItem (OrderItem orderItem);
    OrderItem findOrderItembyId (Long id);
    void deleteOrderItem (Long id);
}
