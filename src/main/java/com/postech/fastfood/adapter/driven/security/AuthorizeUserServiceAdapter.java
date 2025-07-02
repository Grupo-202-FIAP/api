package com.postech.fastfood.adapter.driven.security;

import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizeUserServiceAdapter implements UserDetailsService {

    private final IEmployeeEntityRepository userRepository;
    private final ICustomerEntityRepository customerEntityRepository;
    private final LoggerPort logger;

    @Override
    public UserEntity loadUserByUsername(String email) {
        logger.info("[Auth] Tentando autenticar funcionário com email={}", email);

        return userRepository.findByEmail(email).orElseThrow(() -> {
            logger.warn("[Auth] Funcionário não encontrado com email={}", email);
            return new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND);
        });

    }

    public UserEntity loadCustomerByCpf(String cpf) {
        logger.info("[Auth] Buscando cliente para autenticação via CPF");

        return customerEntityRepository.findByCpf(cpf).orElseThrow(() -> {
            logger.warn("[Auth] Cliente não encontrado com CPF informado");
            return new FastFoodException("Customer not found", "Not Found", HttpStatus.NOT_FOUND);
        });

    }
}
