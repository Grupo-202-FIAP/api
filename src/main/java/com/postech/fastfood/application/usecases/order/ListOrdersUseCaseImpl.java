package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.infrastructure.gateways.order.ListOrdersUseCase;
import java.util.List;

public class ListOrdersUseCaseImpl implements ListOrdersUseCase {

    private final OrderRepository orderRepository;

    public ListOrdersUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> execute() {
        return this.orderRepository.findAll();
    }
}
