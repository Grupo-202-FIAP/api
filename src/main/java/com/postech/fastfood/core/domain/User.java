package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.UserRole;

import java.util.UUID;

public abstract class User {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private UserRole role;

    public User(UUID id, String name, String email, String cpf, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
