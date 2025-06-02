package com.postech.fastfood.adapter.driver.controller.dto.response;

import com.postech.fastfood.core.domain.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ProductsResponse(
        Long id,
        String name,
        @Enumerated(EnumType.STRING)
        Category category,
        BigDecimal unitPrice,
        String urlImage,
        String description,
        UUID employeeCreatorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
