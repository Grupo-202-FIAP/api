package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.infrastructure.gateways.order.ListOrdersUseCase;
import java.util.List;

public class ListOrdersUseCaseImpl implements ListOrdersUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public ListOrdersUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public List<Order> execute() {
        return this.orderRepositoryPort.findAll();
    }
}
