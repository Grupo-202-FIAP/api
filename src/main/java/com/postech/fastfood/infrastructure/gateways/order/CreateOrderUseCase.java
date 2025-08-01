package com.postech.fastfood.core.usecase.order;

import com.postech.fastfood.core.domain.Order;

public interface CreateOrderUseCase {
    Order execute(Order order);
}
