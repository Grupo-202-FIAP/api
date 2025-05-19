package com.postech.fastfood.adapter.driver.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerEmailRequest(
        @Email @NotNull @NotBlank
        String email,
        @NotBlank @NotNull @NotBlank
        String name
) {
}
