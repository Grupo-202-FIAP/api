package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final ICustomerEntityRepository customerEntityRepository;
    private final IEmployeeEntityRepository employeeEntityRepository;
    private final LoggerPort logger;

    public UserRepositoryAdapter(
            ICustomerEntityRepository customerEntityRepository,
            IEmployeeEntityRepository employeeEntityRepository,
            LoggerPort logger) {
        this.customerEntityRepository = customerEntityRepository;
        this.employeeEntityRepository = employeeEntityRepository;
        this.logger = logger;
    }

    private static final String LOG_USUARIO_ENCONTRADO = "[Repository][User] Usuário encontrado: id={}, email={}";

    @Override
    public User save(User user) {
        logger.info("[Repository][User] Iniciando persistência do usuário: tipo={}, email={}",
                user instanceof Employee ? "Employee" : "Customer",
                user.getEmail());

        User userSaved = null;
        if (user instanceof Employee) {
            userSaved = EmployeeMapper.toDomain(this.employeeEntityRepository.save(EmployeeMapper.toEntity((Employee) user)));
        } else if (user instanceof Customer) {
            userSaved = CustomerMapper.toDomain(this.customerEntityRepository.save(CustomerMapper.toEntity((Customer) user)));
        }

        logger.info("[Repository][User] Usuário salvo com sucesso: id={}, tipo={}",
                userSaved.getId(), userSaved instanceof Employee ? "Employee" : "Customer");

        return userSaved;
    }

    public User findByCpf(String cpf, UserRole role) {
        logger.info("[Repository][User] Buscando usuário por CPF={}, role={}", cpf, role);

        User user = null;
        if (role != UserRole.ROLE_GUEST && role != UserRole.ROLE_CUSTOMER) {
            final EmployeeEntity employee = this.employeeEntityRepository
                    .findByCpf(cpf)
                    .orElseThrow(() -> {
                        logger.warn("[Repository][User] Funcionário não encontrado por CPF={}", cpf);

                        return new FastFoodException(
                                "Employee not found with CPF: " + cpf,
                                "Employee Not Found",
                                HttpStatus.NOT_FOUND
                        );
                    });
            user = EmployeeMapper.toDomain(employee);
        } else {
            final CustomerEntity customer = this.customerEntityRepository
                    .findByCpf(cpf)
                    .orElseThrow(() -> {
                        logger.warn("[Repository][User] Cliente não encontrado por CPF={}", cpf);

                        return new FastFoodException(
                                "Customer not found with CPF: " + cpf,
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND
                        );
                    });
            user = CustomerMapper.toDomain(customer);
        }
        logger.info(LOG_USUARIO_ENCONTRADO, user.getId(), user.getEmail());

        return user;
    }


    public User findByEmail(String email, UserRole role) {
        logger.info("[Repository][User] Buscando usuário por email={}, role={}", email, role);

        User user = null;
        if (role != UserRole.ROLE_GUEST && role != UserRole.ROLE_CUSTOMER) {
            final EmployeeEntity employee = this.employeeEntityRepository
                    .findByEmail(email)
                    .orElseThrow(() -> {
                        logger.warn("[Repository][User] Funcionário não encontrado por email={}", email);

                        return new FastFoodException(
                                "Employee not found with Email: " + email,
                                "Employee Not Found",
                                HttpStatus.NOT_FOUND
                        );
                    });
            user = EmployeeMapper.toDomain(employee);
        } else {
            final CustomerEntity customer = this.customerEntityRepository
                    .findByEmail(email)
                    .orElseThrow(() -> {
                        logger.warn("[Repository][User] Cliente não encontrado por email={}", email);

                        return new FastFoodException(
                                "Customer not found with Email: " + email,
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND
                        );
                    });
            user = CustomerMapper.toDomain(customer);
        }
        logger.info(LOG_USUARIO_ENCONTRADO, user.getId(), user.getEmail());

        return user;
    }

    public User findById(UUID id) {
        logger.info("[Repository][User] Buscando usuário por ID={}", id);

        final User user = customerEntityRepository.findById(id)
                .map(CustomerMapper::toDomain)
                .map(User.class::cast)
                .or(() -> employeeEntityRepository.findById(id)
                        .map(EmployeeMapper::toDomain)
                        .map(User.class::cast))
                .orElseThrow(() -> {
                    logger.warn("[Repository][User] Nenhum usuário encontrado com ID={}", id);
                    return new FastFoodException(
                            "User not found with ID: " + id,
                            "User Not Found",
                            HttpStatus.NOT_FOUND
                    );
                });

        logger.info(LOG_USUARIO_ENCONTRADO, user.getId(), user.getEmail());
        return user;
    }
}
