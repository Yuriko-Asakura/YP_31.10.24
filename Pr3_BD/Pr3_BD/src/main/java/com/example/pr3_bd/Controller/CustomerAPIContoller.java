package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/cus")
public class CustomerAPIContoller {
    private final CustomerService customerService;
    public CustomerAPIContoller(CustomerService customerService){
        this.customerService =customerService;
    }

    @GetMapping
    public List<customer> getAllcustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{id}")
    public customer getcustomerById(@PathVariable Long id) {
        return customerService.findCustomerbyId(id);
    }

    @PostMapping
    public customer createcustomer(@RequestBody customer cus) {
        return customerService.createCustomer(cus);
    }

    @PutMapping("/{id}")
    public customer updatecustomer(@PathVariable Long id, @RequestBody customer cus) {
        cus.setId(id);
        return customerService.updateCustomer(cus);
    }

    @DeleteMapping("/{id}")
    public void deletecustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
