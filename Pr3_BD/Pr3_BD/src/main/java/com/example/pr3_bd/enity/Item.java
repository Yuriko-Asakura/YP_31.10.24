package com.example.pr3_bd.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name cannot be blank")
    @Size(max = 100, message = "Item name cannot exceed 100 characters")
    private String itemName;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "item_service",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Service> services = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Item name cannot be blank") @Size(max = 100, message = "Item name cannot exceed 100 characters") String getItemName() {
        return itemName;
    }

    public void setItemName(@NotBlank(message = "Item name cannot be blank") @Size(max = 100, message = "Item name cannot exceed 100 characters") String itemName) {
        this.itemName = itemName;
    }

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 0, message = "Price must be greater than or equal to 0") double price) {
        this.price = price;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
