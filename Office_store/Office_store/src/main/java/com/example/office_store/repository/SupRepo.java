package com.example.office_store.repository;

import com.example.office_store.model.model_Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupRepo extends CrudRepository<model_Supplier, Long> {
}