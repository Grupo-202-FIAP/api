package com.postech.fastfood.application.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.User;
import java.util.Optional;

public interface UserRepositoryPortOut {
    Optional<User> findByCpf(String cpf);
}
