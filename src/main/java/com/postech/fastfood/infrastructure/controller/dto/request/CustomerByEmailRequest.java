package com.postech.fastfood.adapter.driver.controller.dto.request;

import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerByEmailRequest(
        @Email @NotNull @NotBlank
        String email,
        @Enumerated
        UserRole userRole
) {
}
