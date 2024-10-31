package com.example.office_store.repository;

import com.example.office_store.model.model_Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<model_Category, Long> {
}