package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.OrderRequest;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static Order toDomain(OrderEntity orderEntity) {
        return new Order.Builder()
                .id(orderEntity.getId())
                .identifier(orderEntity.getIdentifier())
                .customer(CustomerMapper.toDomain(orderEntity.getCustomer()))
                .itens(orderEntity.getItens()
                        .stream()
                        .map(OrderItemMapper::toDomain).toList())
                .totalPrice(orderEntity.getTotalPrice())
                .payment(PaymentMapper.toDomain(orderEntity.getPayment()))
                .status(orderEntity.getOrderStatus())
                .orderDateTime(orderEntity.getOrderDateTime())
                .updatedAt(orderEntity.getUpdatedAt())
                .build();
    }

    public static OrderEntity toEntity(Order order, PaymentEntity paymentEntity) {
        return OrderEntity.builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .customer(CustomerMapper.toEntity(order.getCustomer()))
                .itens(order.getItens()
                        .stream()
                        .map(OrderItemMapper::toEntity).toList())
                .totalPrice(order.getTotalPrice())
                .payment(paymentEntity)
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .customer(CustomerMapper.toEntity(order.getCustomer()))
                .itens(order.getItens()
                        .stream()
                        .map(OrderItemMapper::toEntity).toList())
                .totalPrice(order.getTotalPrice())
                .payment(PaymentMapper.toEntity(order.getPayment()))
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static Order toDomain(OrderRequest orderRequest) {
        return new Order.Builder()
                .customer(new Customer.Builder()
                        .id(orderRequest.customerId())
                        .build())
                .itens(orderRequest.itens().stream().map(OrderItemMapper::toDomain).toList())
                .build();
    }

}
