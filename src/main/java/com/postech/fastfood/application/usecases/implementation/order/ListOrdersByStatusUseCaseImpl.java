package com.postech.fastfood.application.usecases.implementation.order;

import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.application.usecases.interfaces.order.ListOrdersByStatusUseCase;
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
