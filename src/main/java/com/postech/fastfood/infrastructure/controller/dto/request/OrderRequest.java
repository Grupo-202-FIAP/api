package com.postech.fastfood.infrastructure.controller.dto.request;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        List<OrderItemRequest> itens,
        UUID customerId
) {
}
