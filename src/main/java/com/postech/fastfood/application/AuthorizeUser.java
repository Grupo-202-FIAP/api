package com.postech.fastfood.application;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.ICustomerEntityRepository;
import com.postech.fastfood.core.exception.FastFoodException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizeUser implements UserDetailsService {

    private final ICustomerEntityRepository userRepository;

    @Override
    public CustomerEntity loadUserByUsername(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(() -> new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND));
    }
}
