package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.UserRole;
import java.util.UUID;

public class Customer extends User {
    public Customer(UUID id, String name, String email, String cpf, UserRole role) {
        super(id, name, email, cpf, role);
    }

    public Customer(Builder builder) {
        super(builder.id, builder.name, builder.email, builder.cpf, builder.role);
    }

    public Customer() {
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String email;
        private String cpf;
        private UserRole role;

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


        public Customer build() {
            return new Customer(this);
        }
    }

}
