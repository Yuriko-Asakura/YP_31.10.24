package com.example.pr3_bd.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Service name cannot be blank")
    @Size(max = 100, message = "Service name cannot exceed 100 characters")
    private String serviceName;

    @ManyToMany(mappedBy = "services")
    private Set<Item> items = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "item_service",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Item> itemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Service name cannot be blank") @Size(max = 100, message = "Service name cannot exceed 100 characters") String getServiceName() {
        return serviceName;
    }

    public void setServiceName(@NotBlank(message = "Service name cannot be blank") @Size(max = 100, message = "Service name cannot exceed 100 characters") String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
