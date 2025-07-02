package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.CustomerRepositoryPort;
import com.postech.fastfood.core.ports.LoggerPort;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    public static final String CUSTOMER_NOT_FOUND = "Customer Not Found";
    private final ICustomerEntityRepository customerEntityRepository;
    private final LoggerPort logger;

    public CustomerRepositoryAdapter(
            ICustomerEntityRepository customerEntityRepository,
            LoggerPort logger) {
        this.customerEntityRepository = customerEntityRepository;
        this.logger = logger;
    }

    public Customer save(Customer customer) {
        logger.info("[Repository][Customer] Iniciando persistência do cliente: email={}", customer.getEmail());
        final var entity = CustomerMapper.toEntity(customer);
        final var saved = customerEntityRepository.save(entity);
        logger.info("[Repository][Customer] Cliente salvo com sucesso: id={}", saved.getId());
        return CustomerMapper.toDomain(saved);
    }

    public Customer findByCpf(String cpf) {
        logger.info("[Repository][Customer] Buscando cliente com CPF={}", cpf);
        final var customerEntity = customerEntityRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                            logger.warn("[Repository][Customer] Cliente não encontrado com CPF={}", cpf);

                            return new FastFoodException("Customer not found with CPF: " + cpf, CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
                        }
                );
        logger.info("[Repository][Customer] Cliente encontrado: id={}, email={}", customerEntity.getId(), customerEntity.getEmail());

        return CustomerMapper.toDomain(customerEntity);
    }

    public Customer findByEmail(String email) {
        logger.info("[Repository][Customer] Buscando cliente com email={}", email);

        final CustomerEntity customerEntity = this.customerEntityRepository.findByEmail(email)
                .orElseThrow(() -> {
                            logger.warn("[Repository][Customer] Cliente não encontrado com email={}", email);

                            return new FastFoodException("Customer not found with Email: " + email, CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
                        }
                );
        logger.info("[Repository][Customer] Cliente encontrado: id={}, email={}", customerEntity.getId(), customerEntity.getEmail());

        return CustomerMapper.toDomain(customerEntity);
    }

    public Customer findById(UUID id) {
        logger.info("[Repository][Customer] Buscando cliente por id={}", id);

        final var customerEntity = customerEntityRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Customer] Cliente não encontrado com id={}", id);

                    return new FastFoodException("User not found with ID: " + id, CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
                });
        logger.info("[Repository][Customer] Cliente encontrado: email={}", customerEntity.getEmail());

        return CustomerMapper.toDomain(customerEntity);
    }
}
