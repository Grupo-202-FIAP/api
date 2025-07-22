package com.postech.fastfood.infrastructure.gateways.order;

import com.postech.fastfood.domain.Order;

public interface CreateOrderUseCase {
    Order execute(Order order);
}
