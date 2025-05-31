package com.postech.fastfood.core.usecase.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.util.List;

public interface ListOrdersByStatusUseCase {
    List<Order> execute(OrderStatus status);
}
