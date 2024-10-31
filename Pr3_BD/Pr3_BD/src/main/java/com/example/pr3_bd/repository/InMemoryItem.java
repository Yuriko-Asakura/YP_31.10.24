package com.example.pr3_bd.repository;


import com.example.pr3_bd.enity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryItem {
    private final List<Item> ITEM = new ArrayList<>();
    public List<Item> findAllItem(Long id) {
        return ITEM;
    }

    public Item findItem(Long id) {
        return ITEM
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Item createItem(Item item) {
        ITEM.add(item);
        return item;
    }

    public Item updateItem(Item item) {
        var itemIndex = IntStream.range(0, ITEM.size())
                .filter(index -> ITEM.get(index).getId().equals(item.getId()))
                .findFirst()
                .orElse(-1);
        if (itemIndex == -1) {
            return null;
        }
        ITEM.set(itemIndex, item);
        return item;
    }

    public void deleteItem(Long id) {
        var item = findAllItem(id);
        if (item != null) {
            ITEM.remove(item);
        }
    }
}
