package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;

public interface UserRepositoryPort {
     User save(User user);

     User findByCpf(String cpf, UserRole userRole);

     User findByEmail(String email,UserRole userRole);
}
