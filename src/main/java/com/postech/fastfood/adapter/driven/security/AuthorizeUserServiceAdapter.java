package com.postech.fastfood.adapter.driven.security;

import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.IEmployeeEntityRepository;
import com.postech.fastfood.core.exception.FastFoodException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizeUserServiceAdapter implements UserDetailsService {

    private final IEmployeeEntityRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND));
    }
}
