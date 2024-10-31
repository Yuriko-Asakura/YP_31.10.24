package com.example.office_store.repository;

import com.example.office_store.model.model_Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<model_Customer, Long> {
}
