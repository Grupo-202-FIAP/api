package com.postech.fastfood.adapter.driver.controller.dto.response;

import jakarta.validation.constraints.NotNull;

public record AuthResponse(
        @NotNull
        String token) {
}
