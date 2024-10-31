package com.example.office_store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class model_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<model_Product> products;

    public model_Category(Long id, String name, Set<model_Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public model_Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Category name is required") @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Category name is required") @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters") String name) {
        this.name = name;
    }

    public Set<model_Product> getProducts() {
        return products;
    }

    public void setProducts(Set<model_Product> products) {
        this.products = products;
    }
}
