package com.postech.fastfood.core.service.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.domain.enums.PaymentStatus;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.UpdateOrderStatusUseCase;
import java.util.UUID;

public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public UpdateOrderStatusUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {

        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order execute(UUID orderID) {
        final Order order = orderRepositoryPort.findById(orderID);
        switch (order.getStatus()) {
            case RECEIVED:
                final PaymentStatus status = order.getPayment().getStatus();
                if (status == PaymentStatus.APPROVED) {
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
        return orderRepositoryPort.save(order);
    }
}
