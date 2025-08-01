package com.postech.fastfood.core.service.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.ListOrdersUseCase;
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
