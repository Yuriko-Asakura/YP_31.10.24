package com.example.office_store.repository;


import com.example.office_store.model.ModelUser;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<ModelUser, Long> {
    ModelUser findByUsername(String username);
    boolean existsByUsername(String username);
}
