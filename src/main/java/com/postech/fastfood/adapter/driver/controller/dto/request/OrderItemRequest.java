package com.postech.fastfood.adapter.driver.controller.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequest(
        UUID orderId,
        Long productId,
        Integer quantity,
        BigDecimal priceAtPurchase
) {
}
