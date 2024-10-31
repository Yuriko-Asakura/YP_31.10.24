package com.example.office_store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class model_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @Positive(message = "Stock quantity must be positive")
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private model_Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Product name is required") @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name is required") @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Description is required") @Size(max = 500, message = "Description must be less than 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") @Size(max = 500, message = "Description must be less than 500 characters") String description) {
        this.description = description;
    }

    @Positive(message = "Price must be positive")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Price must be positive") double price) {
        this.price = price;
    }

    @Positive(message = "Stock quantity must be positive")
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@Positive(message = "Stock quantity must be positive") int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public model_Category getCategory() {
        return category;
    }

    public void setCategory(model_Category category) {
        this.category = category;
    }
}
