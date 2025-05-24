package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.ports.OrderRepositoryPort;

public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final IOrderRepository orderRepository;

    public OrderRepositoryAdapter(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderMapper.toEntity(order);
        return OrderMapper.toDomain(orderRepository.save(orderEntity));
    }
}
