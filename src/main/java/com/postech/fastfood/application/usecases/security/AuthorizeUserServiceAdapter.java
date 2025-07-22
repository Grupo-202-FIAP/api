package com.postech.fastfood.application.usecases.security;

import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.infrastructure.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizeUserServiceAdapter implements UserDetailsService {

    private final IEmployeeEntityRepository userRepository;
    private final ICustomerEntityRepository customerEntityRepository;

    @Override
    public UserEntity loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND));
    }

    public UserEntity loadCustomerByCpf(String cpf) {
        return customerEntityRepository.findByCpf(cpf).orElseThrow(
                () -> new FastFoodException("Customer not found", "Not Found", HttpStatus.NOT_FOUND)
        );
    }
}
