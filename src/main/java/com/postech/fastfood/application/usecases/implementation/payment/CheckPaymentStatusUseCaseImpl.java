package com.postech.fastfood.application.usecases.implementation.payment;

import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.application.usecases.interfaces.payment.CheckPaymentStatusUseCase;
import com.postech.fastfood.domain.enums.OrderStatus;

import java.util.UUID;

public class CheckPaymentStatusUseCaseImpl implements CheckPaymentStatusUseCase {

    private OrderRepositoryPort orderRepositoryPort;

    public CheckPaymentStatusUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public String execute(UUID orderId) {
        final OrderStatus status = orderRepositoryPort.findById(orderId).getStatus();
        return status.toString();
    }
}
