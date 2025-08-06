package com.postech.fastfood.adapter.driver.controller.dto.response;

import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderResponse(
        UUID id,
        String identifier,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime orderDateTime,
        UUID customerId,
        UUID paymentId,
        List<OrderItemResponse> items
) {
}
