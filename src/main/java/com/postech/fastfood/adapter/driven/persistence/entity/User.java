package com.postech.fastfood.adapter.driven.persistence.entity;

import static com.postech.fastfood.core.domain.enums.UserRole.ADMIN;
import static com.postech.fastfood.core.domain.enums.UserRole.CUSTOMER;
import static com.postech.fastfood.core.domain.enums.UserRole.GUEST;

import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "users")
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String cpf;
    private UserRole role;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role.equals(ADMIN)) {
            final var roleAdmin = new SimpleGrantedAuthority(ADMIN.getRole());
            final var roleCustomer = new SimpleGrantedAuthority(CUSTOMER.getRole());
            return List.of(roleAdmin, roleCustomer);
        } else {
            final var role = new SimpleGrantedAuthority(GUEST.getRole());
            return List.of(role);
        }
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
