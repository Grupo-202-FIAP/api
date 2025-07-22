package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.domain.enums.PaymentStatus;
import com.postech.fastfood.infrastructure.gateways.order.UpdateOrderStatusUseCase;
import java.util.UUID;

public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

    private final OrderRepository orderRepository;

    public UpdateOrderStatusUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(UUID orderID) {
        final Order order = orderRepository.findById(orderID);
        switch (order.getStatus()) {
            case RECEIVED:
                final PaymentStatus status = order.getPayment().getStatus();
                if (status == PaymentStatus.AUTHORIZED) {
                    order.setStatus(OrderStatus.PREPARING);
                } else {
                    throw new IllegalStateException("Cannot complete order: payment not completed");
                }
                break;
            case PREPARING:
                order.setStatus(OrderStatus.READY);
                break;
            case READY:
                break;
            case COMPLETED:
            case CANCELLED:
                throw new IllegalStateException("Order is already in terminal state: " + order.getStatus());
            default:
                throw new IllegalStateException("Unknown order status: " + order.getStatus());
        }
        return orderRepository.save(order);
    }
}
