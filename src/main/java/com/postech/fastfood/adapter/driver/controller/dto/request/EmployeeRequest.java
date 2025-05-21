package com.postech.fastfood.adapter.driver.controller.dto.request;

import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "CPF is mandatory")
        @CPF
        String cpf,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,
        @NotNull
        @Enumerated
        UserRole userRole
) {
}
