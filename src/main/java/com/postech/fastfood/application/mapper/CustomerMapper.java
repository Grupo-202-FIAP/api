package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.core.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static Customer toDomain(CustomerEntity customerEntity) {
        return new Customer.Builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .cpf(customerEntity.getCpf())
                .role(customerEntity.getRole())
                .build();
    }

    public static CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .role(customer.getRole())
                .build();
    }

    public static Customer toDomain(CustomerCpfRequest customerCpfRequest) {
        return new Customer.Builder()
                .cpf(customerCpfRequest.cpf())
                .role(customerCpfRequest.userRole())
                .build();
    }

    public static Customer toDomain(@Valid CustomerEmailRequest customerEmailRequest) {
        return new Customer.Builder()
                .email(customerEmailRequest.email())
                .name(customerEmailRequest.name())
                .build();
    }
}
