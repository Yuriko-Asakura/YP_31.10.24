package com.example.office_store.repository;

import com.example.office_store.model.model_ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<model_ShoppingCart, Long> {
}