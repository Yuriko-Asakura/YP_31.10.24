package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}