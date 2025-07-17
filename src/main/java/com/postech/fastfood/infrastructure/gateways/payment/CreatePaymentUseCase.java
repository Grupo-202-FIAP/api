package com.postech.fastfood.infrastructure.gateways.payment;

import com.postech.fastfood.infrastructure.controller.dto.request.PaymentRequest;
import java.util.UUID;

public interface CreatePaymentUseCase {
    String execute(UUID orderId, PaymentRequest paymentRequest);
}
