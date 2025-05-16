package com.postech.fastfood.application;

import com.postech.fastfood.adapter.driven.persistence.entity.User;
import com.postech.fastfood.application.repository.UserRepositoryPortOut;
import com.postech.fastfood.core.exception.FastFoodException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutorizaUsuario implements UserDetailsService {

    private final UserRepositoryPortOut userRepository;

    @Override
    public User loadUserByUsername(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(() -> new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND));
    }
}
