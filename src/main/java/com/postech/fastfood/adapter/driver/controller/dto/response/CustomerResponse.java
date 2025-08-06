package com.postech.fastfood.adapter.driver.controller.dto.response;

import com.postech.fastfood.core.domain.enums.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CustomerResponse(
        UUID id,
        String name,
        String email,
        String cpf,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
