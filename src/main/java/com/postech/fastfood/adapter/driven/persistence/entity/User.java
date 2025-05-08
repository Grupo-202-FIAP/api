package com.postech.fastfood.adapter.driven.persistence.entity;

import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.postech.fastfood.core.domain.enums.UserRole.*;

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
    private UserRole role;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role.equals(ADMIN)) {
            var roleAdmin = new SimpleGrantedAuthority(ADMIN.getRole());
            var roleCustomer = new SimpleGrantedAuthority(CUSTOMER.getRole());
            return List.of(roleAdmin, roleCustomer);
        } else {
            var role = new SimpleGrantedAuthority(GUEST.getRole());
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
