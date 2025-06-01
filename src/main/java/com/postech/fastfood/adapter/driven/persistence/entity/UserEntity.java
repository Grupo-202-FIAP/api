package com.postech.fastfood.adapter.driven.persistence.entity;

import static com.postech.fastfood.core.domain.enums.UserRole.ROLE_ADMIN;
import static com.postech.fastfood.core.domain.enums.UserRole.ROLE_CUSTOMER;
import static com.postech.fastfood.core.domain.enums.UserRole.ROLE_GUEST;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class UserEntity implements UserDetails {

    @Serial
    private static final long serialVersionUID = -5814489921556099032L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role.equals(ROLE_ADMIN)) {
            final var roleAdmin = new SimpleGrantedAuthority(ROLE_ADMIN.getRole());
            final var roleCustomer = new SimpleGrantedAuthority(ROLE_CUSTOMER.getRole());
            return List.of(roleAdmin, roleCustomer);
        } else {
            final var role = new SimpleGrantedAuthority(ROLE_GUEST.getRole());
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
