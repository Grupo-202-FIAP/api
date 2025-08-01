package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;

public class Employee extends User {

    private String password;

    public Employee(
            UUID id,
            String name,
            String email,
            String cpf,
            UserRole role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String password
    ) {
        super(id, name, email, cpf, role, createdAt, updatedAt);
        this.password = password;
    }

    public Employee() {
    }

    public Employee(Builder builder) {
        super(builder.id, builder.name, builder.email, builder.cpf, builder.role, builder.createdAt, builder.updatedAt);
        this.password = builder.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String email;
        private String cpf;
        private UserRole role;
        private String password;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(UUID id) {
            this.id = id;
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

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
