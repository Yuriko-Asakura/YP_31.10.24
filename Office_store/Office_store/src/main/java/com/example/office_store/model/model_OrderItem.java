package com.example.office_store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class model_OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private model_Product product;

    @NotNull(message = "Order is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private model_Order order;

    private int quantity;

    public model_OrderItem(Long id, model_Product product, model_Order order, int quantity) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public model_OrderItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Product is required") model_Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull(message = "Product is required") model_Product product) {
        this.product = product;
    }

    public @NotNull(message = "Order is required") model_Order getOrder() {
        return order;
    }

    public void setOrder(@NotNull(message = "Order is required") model_Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
