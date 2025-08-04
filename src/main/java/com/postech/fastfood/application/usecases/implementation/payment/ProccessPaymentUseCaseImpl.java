package com.postech.fastfood.application.usecases.implementation.payment;

import com.postech.fastfood.application.gateways.PaymentRepositoryPort;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.application.usecases.interfaces.payment.ProccessPaymentUseCase;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class ProccessPaymentUseCaseImpl implements ProccessPaymentUseCase {

    private final PaymentRepositoryPort paymentRepositoryPort;

    public ProccessPaymentUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public void execute(UUID orderId) {
        try {
            this.paymentRepositoryPort.save(orderId);
        } catch (FastFoodException e) {
            throw new FastFoodException(e.getMessage(), "Error realizing payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
