package com.example.office_store.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, MENED;

    @Override
    public String getAuthority() {
        return name();
    }
}
