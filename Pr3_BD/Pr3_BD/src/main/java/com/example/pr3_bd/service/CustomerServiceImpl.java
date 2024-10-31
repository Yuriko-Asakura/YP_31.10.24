package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Order;
import com.example.pr3_bd.enity.Profile;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<customer> findAllCustomer() {
        return repository.findAll();
    }

    @Override
    public customer createCustomer(customer customer) {
        return repository.save(customer);
    }

    @Override
    public customer updateCustomer(customer customer) {
        return repository.save(customer);
    }

    @Override
    public customer findCustomerbyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
