package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryOrderItem {
    private final List<OrderItem> ORDERITEM = new ArrayList<>();
    public List<OrderItem> findAllOrderItem(Long id) {
        return ORDERITEM;
    }

    public OrderItem findOrderItem(Long id) {
        return ORDERITEM
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public OrderItem createOrderItem(OrderItem orderItem) {
        ORDERITEM.add(orderItem);
        return orderItem;
    }

    public OrderItem updateOrderItem(OrderItem orderItem) {
        var orderItemIndex = IntStream.range(0, ORDERITEM.size())
                .filter(index -> ORDERITEM.get(index).getId().equals(orderItem.getId()))
                .findFirst()
                .orElse(-1);
        if (orderItemIndex == -1) {
            return null;
        }
        ORDERITEM.set(orderItemIndex, orderItem);
        return orderItem;
    }

    public void deleteOrderItem(Long id) {
        var orderItems = findAllOrderItem(id);
        if (orderItems != null) {
            ORDERITEM.remove(orderItems);
        }
    }

}
