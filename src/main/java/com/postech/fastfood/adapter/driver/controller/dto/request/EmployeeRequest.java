package com.postech.fastfood.adapter.driver.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EmployeeRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "CPF is mandatory")
        @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
        String cpf,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,
        @NotBlank(message = "Role is mandatory")
        String role
) {
}
