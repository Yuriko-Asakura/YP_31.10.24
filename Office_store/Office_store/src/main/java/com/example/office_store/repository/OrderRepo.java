package com.example.office_store.repository;

import com.example.office_store.model.model_Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<model_Order, Long> {
}