package com.example.pr3_bd.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address cannot exceed 100 characters")
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is not valid")
    private String phoneNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Address cannot be blank") @Size(max = 100, message = "Address cannot exceed 100 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address cannot be blank") @Size(max = 100, message = "Address cannot exceed 100 characters") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Phone number cannot be blank") @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is not valid") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number cannot be blank") @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is not valid") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
