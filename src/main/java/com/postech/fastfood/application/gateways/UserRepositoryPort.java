package com.postech.fastfood.application.gateways;

import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);

    User findByCpf(String cpf, UserRole userRole);

    User findByEmail(String email, UserRole userRole);

    User findById(UUID id);
}
