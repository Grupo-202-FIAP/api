package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.CustomerNotFoundException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final ICustomerEntityRepository iCustomerEntityRepository;
    private final IEmployeeEntityRepository iEmployeeEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryAdapter(ICustomerEntityRepository iCustomerEntityRepository, IEmployeeEntityRepository iEmployeeEntityRepositor, PasswordEncoder passwordEncoder) {
        this.iCustomerEntityRepository = iCustomerEntityRepository;
        this.iEmployeeEntityRepository = iEmployeeEntityRepositor;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        User userSaved = null;
        if (user instanceof Employee) {
            userSaved = EmployeeMapper.toDomain(this.iEmployeeEntityRepository.save(EmployeeMapper.toEntity((Employee) user)));
        } else if (user instanceof Customer) {
            userSaved = CustomerMapper.toDomain(this.iCustomerEntityRepository.save(CustomerMapper.toEntity((Customer) user)));
        }
        return userSaved;
    }

    public User findByCpf(String cpf, UserRole role) {
        User user = null;
        if (role != UserRole.GUEST && role != UserRole.CUSTOMER) {
            EmployeeEntity employee = this.iEmployeeEntityRepository.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
            user = EmployeeMapper.toDomain(employee);
        } else {
            CustomerEntity customer = this.iCustomerEntityRepository.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
            user = CustomerMapper.toDomain(customer);
        }
        return user;
    }


    public User findByEmail(String email, UserRole role) {
        User user = null;
        if (role != UserRole.GUEST && role != UserRole.CUSTOMER) {
            EmployeeEntity employee = this.iEmployeeEntityRepository.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException("Customer not found with Email: " + email));
            user = EmployeeMapper.toDomain(employee);
        } else {
            CustomerEntity customer = this.iCustomerEntityRepository.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException("Customer not found with Email: " + email));
            user = CustomerMapper.toDomain(customer);
        }
        return user;
    }


}
