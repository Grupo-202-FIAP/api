package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.util.List;
import java.util.UUID;

public interface OrderRepositoryPort {
    Order save(Order order);

    Order findById(UUID orderId);

    List<Order> findAll();

    List<Order> findByStatus(OrderStatus status);
}
