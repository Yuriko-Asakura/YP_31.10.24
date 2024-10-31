package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<customer, Long> {
}
