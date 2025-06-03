package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.EmployeeRequest;
import com.postech.fastfood.core.domain.Employee;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public static Employee toDomain(EmployeeEntity employeeEntity) {
        return new Employee.Builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .email(employeeEntity.getEmail())
                .cpf(employeeEntity.getCpf())
                .password(employeeEntity.getPassword())
                .role(employeeEntity.getRole())
                .createdAt(employeeEntity.getCreatedAt())
                .updatedAt(employeeEntity.getUpdatedAt())
                .build();
    }

    public static EmployeeEntity toEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .cpf(employee.getCpf())
                .password(employee.getPassword())
                .role(employee.getRole())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public static Employee toDomain(@Valid EmployeeRequest employeeRequest) {
        return new Employee.Builder()
                .name(employeeRequest.name())
                .email(employeeRequest.email())
                .cpf(employeeRequest.cpf())
                .role(employeeRequest.userRole())
                .password(employeeRequest.password())
                .build();
    }
}
