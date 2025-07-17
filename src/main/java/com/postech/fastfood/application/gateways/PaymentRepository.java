package com.postech.fastfood.application.gateways;

import com.postech.fastfood.infrastructure.controller.dto.request.PaymentRequest;
import java.util.UUID;

public interface PaymentRepository {
    String create(UUID orderId, PaymentRequest paymentRequest);

    void save(UUID orderId);
}
