package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.CustomerRepositoryPort;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    public static final String CUSTOMER_NOT_FOUND = "Customer Not Found";
    private final ICustomerEntityRepository customerEntityRepository;

    public CustomerRepositoryAdapter(ICustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    public Customer save(Customer customer) {
        final var entity = CustomerMapper.toEntity(customer);
        final var saved = customerEntityRepository.save(entity);
        return CustomerMapper.toDomain(saved);
    }

    public Customer findByCpf(String cpf) {
        final var customerEntity = customerEntityRepository.findByCpf(cpf).orElseThrow(
                () -> new FastFoodException("Customer not found with CPF: " + cpf, CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
        return CustomerMapper.toDomain(customerEntity);
    }

    public Customer findByEmail(String email) {
        final CustomerEntity customerEntity = this.customerEntityRepository.findByEmail(email).orElse(null);
        return CustomerMapper.toDomain(customerEntity);
    }

    public Customer findById(UUID id) {
        final var customerEntity = customerEntityRepository.findById(id)
                .orElseThrow(() -> new FastFoodException("User not found with ID: " + id, CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return CustomerMapper.toDomain(customerEntity);
    }
}
