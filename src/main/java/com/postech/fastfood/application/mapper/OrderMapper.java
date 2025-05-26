package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.core.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static Order toDomain(OrderEntity orderEntity) {
        return new Order.Builder()
                .id(orderEntity.getOrderId())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getOrderStatus())
                .orderDateTime(orderEntity.getOrderDateTime())
                .userId(orderEntity.getUserFkId())
                .paymentId(orderEntity.getPayment() != null ? orderEntity.getPayment().getId() : null)
                .build();
    }

    public static OrderEntity toEntity(Order order, PaymentEntity paymentEntity) {
        return OrderEntity.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .payment(paymentEntity)
                .build();
    }

}
