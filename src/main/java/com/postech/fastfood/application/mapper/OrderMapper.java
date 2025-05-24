package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.core.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .customer(CustomerMapper
                        .toEntity(order.getCustomer())
                )
                .itens(order.getItens()
                        .stream()
                        .map(OrderItemMapper::toEntity).toList())
                .totalPrice(order.getTotalPrice())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public static Order toDomain(OrderEntity order) {
        return new Order.Builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .customer(CustomerMapper.toDomain(order.getCustomer()))
                .itens(order.getItens().stream().map(OrderItemMapper::toDomain).toList())
                .totalPrice(order.getTotalPrice())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
