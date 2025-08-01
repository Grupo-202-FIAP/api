package com.postech.fastfood.infrastructure.gateways.order;

import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import java.util.List;

public interface ListOrdersByStatusUseCase {
    List<Order> execute(OrderStatus status);
}
