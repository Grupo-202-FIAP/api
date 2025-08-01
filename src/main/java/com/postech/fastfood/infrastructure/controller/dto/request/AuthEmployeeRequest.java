package com.postech.fastfood.adapter.driver.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthEmployeeRequest(
        @Email
        String email,
        @NotBlank
        String password
) {
}
