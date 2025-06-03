package com.postech.fastfood.core.service.payment;

import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import com.postech.fastfood.core.usecase.payment.ProccessPaymentUseCase;
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
            throw new FastFoodException(
                    e.getMessage(),
                    "Error realizing payment",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
