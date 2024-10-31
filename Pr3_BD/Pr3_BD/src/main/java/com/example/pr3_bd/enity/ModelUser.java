//package com.example.pr3_bd.enity;
//
//import com.example.pr3_bd.enity.RoleEnum;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.util.Set;
//
//@Entity
//public class ModelUser   {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idUser  ;
//
//    @NotBlank(message = "Username is required")
//    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
//    private String username;
//
//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
//    private String password;
//
//    private boolean active;
//
//    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<RoleEnum> roles;
//
//    public ModelUser  () {}
//
//    public ModelUser  (String username, String password, boolean active, Set<RoleEnum> roles) {
//        this.username = username;
//        this.password = password;
//        this.active = active;
//        this.roles = roles;
//    }
//
//    public Long getIdUser  () {
//        return idUser  ;
//    }
//
//    public void setIdUser  (Long idUser  ) {
//        this.idUser   = idUser  ;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public Set<RoleEnum> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<RoleEnum> roles) {
//        this.roles = roles;
//    }
//}
