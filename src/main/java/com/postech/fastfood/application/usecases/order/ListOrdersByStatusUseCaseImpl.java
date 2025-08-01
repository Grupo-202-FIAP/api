package com.postech.fastfood.core.service.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.ListOrdersByStatusUseCase;
import java.util.List;

public class ListOrdersByStatusUseCaseImpl implements ListOrdersByStatusUseCase {
    private final OrderRepositoryPort orderRepositoryPort;

    public ListOrdersByStatusUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public List<Order> execute(OrderStatus status) {
        return this.orderRepositoryPort.findByStatus(status);
    }
}
