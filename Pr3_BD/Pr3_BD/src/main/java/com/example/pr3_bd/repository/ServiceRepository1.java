package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository1 extends CrudRepository<Service, Long> {
Service findByserviceName(String serviceName);
}
