package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import com.postech.fastfood.core.domain.Payment;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public static Payment toDomain(PaymentEntity paymentEntity) {
        return new Payment.Builder()
                .id(paymentEntity.getId())
                .paymentMethod(paymentEntity.getPaymentMethod())
                .paymentDateTime(paymentEntity.getPaymentDateTime())
                .build();
    }

    public static PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDateTime(payment.getPaymentDateTime())
                .build();
    }

    public static Payment toDomain(@Valid PaymentRequest paymentRequest) {
        return new Payment.Builder()
                .paymentMethod(paymentRequest.paymentMethod())
                .build();
    }
}
