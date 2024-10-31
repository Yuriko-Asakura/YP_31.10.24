package com.example.office_store.repository;

import com.example.office_store.model.model_OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderitemRepo extends CrudRepository<model_OrderItem, Long> {
}