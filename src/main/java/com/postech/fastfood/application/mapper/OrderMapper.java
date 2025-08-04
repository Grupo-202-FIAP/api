package com.postech.fastfood.application.mapper;

import com.postech.fastfood.domain.Customer;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.Payment;
import com.postech.fastfood.domain.enums.PaymentMethod;
import com.postech.fastfood.domain.enums.PaymentStatus;
import com.postech.fastfood.infrastructure.controller.dto.request.OrderRequest;
import com.postech.fastfood.infrastructure.controller.dto.response.OrderResponse;
import com.postech.fastfood.infrastructure.http.mercadopago.dto.*;
import com.postech.fastfood.infrastructure.persistence.entity.OrderEntity;
import com.postech.fastfood.infrastructure.persistence.entity.PaymentEntity;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order toDomain(OrderEntity orderEntity) {
        Order order = new Order.Builder()
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
        if (order.getItens() != null) {
            order.getItens().forEach(item -> item.setOrder(order));
        }
        return order;
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
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .customer(CustomerMapper.toEntity(order.getCustomer()))
                .payment(PaymentMapper.toEntity(order.getPayment()))
                .itens(order.getItens()
                        .stream()
                        .map(OrderItemMapper::toEntity)
                        .toList())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .updatedAt(order.getUpdatedAt())
                .build();


        if (orderEntity.getItens() != null) {
            orderEntity.getItens().forEach(item -> item.setOrder(orderEntity));
        }

        return orderEntity;
    }

    public static Order toDomain(OrderRequest orderRequest) {
        return new Order.Builder()
                .payment(new Payment.Builder()
                        .status(PaymentStatus.PENDING)
                        .paymentMethod(PaymentMethod.QR_CODE)
                        .build())
                .customer(new Customer.Builder()
                        .id(orderRequest.customerId())
                        .build())
                .itens(orderRequest.itens().stream().map(OrderItemMapper::toDomain).toList())
                .build();
    }

    public static OrderEntity toEntityWithoutItems(Order order) {
        if (order == null) {
            return null;
        }

        return OrderEntity.builder()
                .id(order.getId())
                .orderStatus(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .identifier(order.getIdentifier())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderDateTime(order.getOrderDateTime())
                .customerId(order.getCustomer() == null ? null : order.getCustomer().getId())
                .paymentId(order.getPayment() == null ? null : order.getPayment().getId())
                .items(order.getItens().stream()
                        .map(OrderItemMapper::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public static OrderMercadoPagoRequestDto toMercadoPagoV1OrderRequest(Order order, String posId, String mode) {

        var items = order.getItens().stream().map(item ->
                ItemDto.builder()
                        .title(item.getProduct().getName())
                        .unit_price(item.getProduct().getUnitPrice().toString())
                        .quantity(item.getQuantity())
                        .unit_measure("UN")
                        .external_code(item.getProduct().getId().toString())
                        .external_categories(
                                List.of(CategoryIdDto.builder().id(item.getProduct().getCategory().getCategory()).build())
                        )
                        .build()
        ).collect(java.util.stream.Collectors.toList());

        ConfigDto config = ConfigDto.builder()
                .qr(
                        QrConfigDto.builder()
                                .external_pos_id(posId)
                                .mode(mode)
                                .build())
                .build();
        PaymentDto paymentDto = PaymentDto.builder()
                .amount(order.getTotalPrice().toString())
                .build();
        List<PaymentDto> paymentDtos = List.of(paymentDto);

        TransactionsDto transactionsDto = TransactionsDto.builder()
                .payments(paymentDtos)
                .build();

        return OrderMercadoPagoRequestDto.builder()
                .type("qr")
                .total_amount(order.getTotalPrice().toString())
                .description("Pedido FastFood - " + order.getIdentifier())
                .external_reference(order.getIdentifier())
                .expiration_time("PT2H") // 2 horas expiração
                .config(config)
                .transactions(transactionsDto)
                .items(items)
                .build();
    }
}
