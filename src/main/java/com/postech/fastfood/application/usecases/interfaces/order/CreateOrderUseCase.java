package com.postech.fastfood.application.usecases.interfaces.order;

import com.postech.fastfood.domain.Order;

public interface CreateOrderUseCase {
    Order execute(Order order);
}
