package com.example.office_store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class model_ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User  is required")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ModelUser user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private model_Product productID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "User  is required") ModelUser getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "User  is required") ModelUser user) {
        this.user = user;
    }

    public model_Product getProductID() {
        return productID;
    }

    public void setProductID(model_Product productID) {
        this.productID = productID;
    }
}
