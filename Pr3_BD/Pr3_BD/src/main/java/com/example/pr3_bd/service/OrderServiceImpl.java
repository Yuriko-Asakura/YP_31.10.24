package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Order;
import com.example.pr3_bd.repository.OrderRepository;
import com.example.pr3_bd.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAllOrder() {
        return repository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Order findOrderbyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
