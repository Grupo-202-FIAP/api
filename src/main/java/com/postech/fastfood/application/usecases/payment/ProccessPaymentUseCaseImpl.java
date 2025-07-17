package com.postech.fastfood.application.usecases.payment;

import com.postech.fastfood.application.gateways.PaymentRepository;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.payment.ProccessPaymentUseCase;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class ProccessPaymentUseCaseImpl implements ProccessPaymentUseCase {

    private final PaymentRepository paymentRepository;

    public ProccessPaymentUseCaseImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void execute(UUID orderId) {
        try {
            this.paymentRepository.save(orderId);
        } catch (FastFoodException e) {
            throw new FastFoodException(e.getMessage(), "Error realizing payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
