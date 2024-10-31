package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

