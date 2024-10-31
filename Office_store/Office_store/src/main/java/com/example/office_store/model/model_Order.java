package com.example.office_store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class model_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private model_Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<model_OrderItem> orderItems;

    public model_Order(Long id, LocalDate orderDate, model_Customer customer, Set<model_OrderItem> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public model_Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Order date is required") LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotNull(message = "Order date is required") LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public model_Customer getCustomer() {
        return customer;
    }

    public void setCustomer(model_Customer customer) {
        this.customer = customer;
    }

    public Set<model_OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<model_OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
