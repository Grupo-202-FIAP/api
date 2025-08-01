package com.postech.fastfood.infrastructure.controller.dto.response;

import jakarta.validation.constraints.NotNull;

public record AuthResponse(
        @NotNull
        String token) {
}
