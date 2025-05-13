package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.exception.CustomerNotFoundException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserEntityRepository userEntityRepository;

    public UserRepositoryAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }
    @Override
    public User saveCustomer(User user) {
        UserEntity userSaved = this.userEntityRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userSaved);
    }
    @Override
    public User findCustomerByCpf(String cpf) {
        return userEntityRepository.findByCpf(cpf)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
    }

    @Override
    public User findCustomerByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with Email: " + email));
    }


}
