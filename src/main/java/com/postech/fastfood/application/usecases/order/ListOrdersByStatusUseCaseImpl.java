package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.infrastructure.gateways.order.ListOrdersByStatusUseCase;
import java.util.List;

public class ListOrdersByStatusUseCaseImpl implements ListOrdersByStatusUseCase {
    private final OrderRepository orderRepository;

    public ListOrdersByStatusUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> execute(OrderStatus status) {
        return this.orderRepository.findByStatus(status);
    }
}
