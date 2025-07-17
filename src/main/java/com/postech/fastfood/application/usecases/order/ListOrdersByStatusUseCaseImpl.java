package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.infrastructure.gateways.order.ListOrdersByStatusUseCase;
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
