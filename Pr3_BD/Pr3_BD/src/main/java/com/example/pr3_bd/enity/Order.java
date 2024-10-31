package com.example.pr3_bd.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;
@Table(name = "customer_order")
@Entity
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Order date cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Order date must be in the format YYYY-MM-DD")
    private String orderDate;

    @ManyToOne @JoinColumn(name = "customer_id")
    private customer customer;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Order date cannot be blank") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Order date must be in the format YYYY-MM-DD") String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotBlank(message = "Order date cannot be blank") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Order date must be in the format YYYY-MM-DD") String orderDate) {
        this.orderDate = orderDate;
    }

    public com.example.pr3_bd.enity.customer getCustomer() {
        return customer;
    }

    public void setCustomer(com.example.pr3_bd.enity.customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
