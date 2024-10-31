package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.OrderItem;
import com.example.pr3_bd.repository.CustomerRepository;
import com.example.pr3_bd.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService{
    private final OrderItemRepository repository;

    public OrderItemServiceImpl(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderItem> findAllOrderItemr() {
        return repository.findAll();
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public OrderItem findOrderItembyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrderItem(Long id) {
        repository.deleteById(id);
    }
}
