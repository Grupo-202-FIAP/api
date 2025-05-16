package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.User;

public interface UserRepositoryPort {
     User save(User user);

     User findByCpf(String cpf);

     User findByEmail(String email);
}
