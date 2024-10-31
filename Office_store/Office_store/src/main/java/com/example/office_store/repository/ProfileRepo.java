package com.example.office_store.repository;

import com.example.office_store.model.model_Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepo extends CrudRepository<model_Profile, Long> {
}