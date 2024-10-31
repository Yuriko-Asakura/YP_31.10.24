package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.customer;

import java.util.List;

public interface CustomerService {
    List<customer> findAllCustomer();
    customer createCustomer (customer customer);
    customer updateCustomer (customer customer);
    customer findCustomerbyId (Long id);
    void deleteCustomer (Long id);
}
