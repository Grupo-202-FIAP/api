package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.core.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static Customer toDomain(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        return new Customer.Builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .cpf(customerEntity.getCpf())
                .role(customerEntity.getRole())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .build();
    }

    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .role(customer.getRole())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public static Customer toDomain(CustomerCpfRequest customerCpfRequest) {
        if (customerCpfRequest == null) {
            return null;
        }
        return new Customer.Builder()
                .cpf(customerCpfRequest.cpf())
                .role(customerCpfRequest.userRole())
                .build();
    }

    public static Customer toDomain(CustomerEmailRequest customerEmailRequest) {
        if (customerEmailRequest == null) {
            return null;
        }
        return new Customer.Builder()
                .email(customerEmailRequest.email())
                .name(customerEmailRequest.name())
                .build();
    }
}
