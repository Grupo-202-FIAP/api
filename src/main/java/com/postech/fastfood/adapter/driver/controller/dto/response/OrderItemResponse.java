package com.postech.fastfood.adapter.driver.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderItemResponse(
    UUID id,
    ProductsResponse product,
    int quantity,
    BigDecimal priceAtPurchase
) {
}
