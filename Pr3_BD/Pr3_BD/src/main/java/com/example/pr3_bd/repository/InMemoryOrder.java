package com.example.pr3_bd.repository;


import com.example.pr3_bd.enity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryOrder {
    private final List<Order> ORDER = new ArrayList<>();
    public List<Order> findAllOrder(Long id) {
        return ORDER;
    }

    public Order findOrder(Long id) {
        return ORDER
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Order createOrder(Order order) {
        ORDER.add(order);
        return order;
    }

    public Order updateOrder(Order order) {
        var itemIndex = IntStream.range(0, ORDER.size())
                .filter(index -> ORDER.get(index).getId().equals(order.getId()))
                .findFirst()
                .orElse(-1);
        if (itemIndex == -1) {
            return null;
        }
        ORDER.set(itemIndex, order);
        return order;
    }

    public void deleteOrder(Long id) {
        var order = findAllOrder(id);
        if (order != null) {
            ORDER.remove(order);
        }
    }
}
