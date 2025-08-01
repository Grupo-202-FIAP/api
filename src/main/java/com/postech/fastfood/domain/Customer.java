package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;

public class Customer extends User {
    public Customer(UUID id, String name, String email, String cpf, UserRole role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, email, cpf, role, createdAt, updatedAt);
    }

    public Customer(Builder builder) {
        super(builder.id, builder.name, builder.email, builder.cpf, builder.role, builder.createdAt, builder.updatedAt);
    }

    public Customer() {
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String email;
        private String cpf;
        private UserRole role;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }


        public Customer build() {
            return new Customer(this);
        }
    }

}
