package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.core.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static Order toDomain(OrderEntity orderEntity) {
        return new Order.Builder()
                .id(orderEntity.getId())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getOrderStatus())
                .orderDateTime(orderEntity.getOrderDateTime())
                .customer(CustomerMapper.toDomain(orderEntity.getCustomer()))
                .build();
    }

    public static OrderEntity toEntity(Order order, PaymentEntity paymentEntity) {
        return OrderEntity.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .payment(paymentEntity)
                .build();
    }

    // TODO FIX MAPPER AND ADD FIELDS TO DOMAIN AND ENTITY
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
                .payment(PaymentMapper.toEntity(order.getPayment()))
                .updatedAt(null)
                .build();
    }

}
