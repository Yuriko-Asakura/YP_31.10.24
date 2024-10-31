package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Item;
import com.example.pr3_bd.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> findAllItem() {
        return (List<Item>) repository.findAll();
    }

    @Override
    public List<Item> getallItemsById(List<Long> itemsIds) {
        return repository.findAllById(itemsIds);
    }

    @Override
    public Item createItem(Item item) {
        return repository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return repository.save(item);
    }

    @Override
    public Item findItembyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
