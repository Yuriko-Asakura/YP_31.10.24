package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository1 extends CrudRepository<Item, Long> {
    Item findByItemName( String ItemName);
}
