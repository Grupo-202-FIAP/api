package com.postech.fastfood.adapter.driver.controller.dto.request;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        List<OrderItemRequest> itens,
        UUID customerId
) {
}
