package com.postech.fastfood.core.service.payment;

import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import com.postech.fastfood.core.usecase.payment.CreatePaymentUseCase;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;

    public CreatePaymentUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public String execute(UUID orderId, PaymentRequest paymentRequest) {
        try {
            return this.paymentRepositoryPort.create(orderId, paymentRequest);
        } catch (FastFoodException e) {
            throw new FastFoodException(
                    e.getMessage(),
                    "Error creating payment",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
