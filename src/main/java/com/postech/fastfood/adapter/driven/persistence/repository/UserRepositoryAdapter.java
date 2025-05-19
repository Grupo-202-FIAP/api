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
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final ICustomerEntityRepository customerEntityRepository;
    private final IEmployeeEntityRepository employeeEntityRepository;


    public UserRepositoryAdapter(ICustomerEntityRepository customerEntityRepository, IEmployeeEntityRepository employeeEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
        this.employeeEntityRepository = employeeEntityRepository;

    }

    @Override
    public User save(User user) {
        User userSaved = null;
        if (user instanceof Employee) {
            userSaved = EmployeeMapper.toDomain(this.employeeEntityRepository.save(EmployeeMapper.toEntity((Employee) user)));
        } else if (user instanceof Customer) {
            userSaved = CustomerMapper.toDomain(this.customerEntityRepository.save(CustomerMapper.toEntity((Customer) user)));
        }
        return userSaved;
    }

    public User findByCpf(String cpf, UserRole role) {
        User user = null;
        if (role != UserRole.GUEST && role != UserRole.CUSTOMER) {
            final EmployeeEntity employee = this.employeeEntityRepository
                    .findByCpf(cpf)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
            user = EmployeeMapper.toDomain(employee);
        } else {
            final CustomerEntity customer = this.customerEntityRepository
                    .findByCpf(cpf)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
            user = CustomerMapper.toDomain(customer);
        }
        return user;
    }


    public User findByEmail(String email, UserRole role) {
        User user = null;
        if (role != UserRole.GUEST && role != UserRole.CUSTOMER) {
            final EmployeeEntity employee = this.employeeEntityRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with Email: " + email));
            user = EmployeeMapper.toDomain(employee);
        } else {
            final CustomerEntity customer = this.customerEntityRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with Email: " + email));
            user = CustomerMapper.toDomain(customer);
        }
        return user;
    }


}
