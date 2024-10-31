package com.example.pr3_bd.service;


import com.example.pr3_bd.enity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAllItem();
    List<Item> getallItemsById(List<Long> itemsIds);
    Item createItem (Item item);
    Item updateItem (Item item);
    Item findItembyId (Long id);
    void deleteItem (Long id);
}
