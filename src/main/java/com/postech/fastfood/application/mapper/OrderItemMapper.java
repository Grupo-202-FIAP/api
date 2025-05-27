package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderItemEntity;
import com.postech.fastfood.core.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    static OrderItemEntity toEntity(OrderItem orderItem) {
        return OrderItemEntity.builder()
                .id(orderItem.getId())
                .order(OrderMapper.toEntity(orderItem.getOrder()))
                .product(ProductMapper.toEntity(orderItem.getProduct()))
                .priceAtPurchase(orderItem.getPriceAtPurchase())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static OrderItem toDomain(OrderItemEntity orderItem) {
        return new OrderItem.Builder()
                .id(orderItem.getId())
                .order(OrderMapper.toDomain(orderItem.getOrder()))
                .product(ProductMapper.toDomain(orderItem.getProduct()))
                .priceAtPurchase(orderItem.getPriceAtPurchase())
                .quantity(orderItem.getQuantity())
                .build();
    }
}

