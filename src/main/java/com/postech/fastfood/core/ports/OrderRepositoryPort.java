package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Order;

public interface OrderRepositoryPort {
    Order save(Order order);
}
