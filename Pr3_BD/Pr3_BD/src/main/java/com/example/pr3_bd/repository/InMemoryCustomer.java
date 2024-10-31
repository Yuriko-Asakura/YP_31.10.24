package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryCustomer {
    private final List<customer> CUSTOMER = new ArrayList<>();
    public List<customer> findAllCustomer(Long id) {
        return CUSTOMER;
    }

    public customer findCustomer(Long id) {
        return CUSTOMER
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public customer createCustomer(customer customer) {
        CUSTOMER.add(customer);
        return customer;
    }

    public customer updateCustomer(customer customer) {
        var customerIndex = IntStream.range(0, CUSTOMER.size())
                .filter(index -> CUSTOMER.get(index).getId().equals(customer.getId()))
                .findFirst()
                .orElse(-1);
        if (customerIndex == -1) {
            return null;
        }
        CUSTOMER.set(customerIndex, customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        var customer = findAllCustomer(id);
        if (customer != null) {
            CUSTOMER.remove(customer);
        }
    }
}
