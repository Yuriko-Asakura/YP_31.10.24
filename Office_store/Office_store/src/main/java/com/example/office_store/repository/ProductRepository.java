package com.example.office_store.repository;

import com.example.office_store.model.model_Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<model_Product, Long> {
}
