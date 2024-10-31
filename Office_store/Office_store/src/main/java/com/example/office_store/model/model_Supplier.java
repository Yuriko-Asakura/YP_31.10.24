package com.example.office_store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class model_Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Supplier name is required")
    @Size(min = 2, max = 100, message = "Supplier name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Contact information is required")
    private String contactInfo;

    public model_Supplier(Long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public model_Supplier() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Supplier name is required") @Size(min = 2, max = 100, message = "Supplier name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Supplier name is required") @Size(min = 2, max = 100, message = "Supplier name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Contact information is required") String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(@NotBlank(message = "Contact information is required") String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
