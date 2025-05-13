package com.postech.fastfood.core.domain;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private String role;

    public User(UUID id, String password, String name, String email, String cpf, String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.name =builder.name;
        this.email = builder.email;
        this.cpf = builder.cpf;
        this.role = builder.role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public static class Builder {
        private UUID id;
        private String password;
        private String name;
        private String email;
        private String cpf;
        private String role;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
