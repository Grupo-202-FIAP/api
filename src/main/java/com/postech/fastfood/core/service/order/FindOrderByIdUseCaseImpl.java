package com.postech.fastfood.core.service.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.FindOrderByIdUseCase;
import java.util.UUID;

public class FindOrderByIdUseCaseImpl implements FindOrderByIdUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindOrderByIdUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order execute(UUID orderId) {
        return this.orderRepositoryPort.findByOrderId(orderId);
    }
}
