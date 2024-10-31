package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Item;
import com.example.pr3_bd.enity.Service;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
