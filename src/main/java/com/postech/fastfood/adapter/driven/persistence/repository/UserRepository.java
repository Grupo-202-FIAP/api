package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.User;
import com.postech.fastfood.application.repository.UserRepositoryPortOut;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepository implements UserRepositoryPortOut {

    private final IUserRepository userRepositoryImpl;

    public Optional<User> findByCpf(String cpf) {
        return userRepositoryImpl.findByCpf(cpf);
    }
}
