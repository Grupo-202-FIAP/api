package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.infrastructure.gateways.order.FindOrderByIdUseCase;
import java.util.UUID;

public class FindOrderByIdUseCaseImpl implements FindOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public FindOrderByIdUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(UUID orderId) {
        return this.orderRepository.findById(orderId);
    }
}
