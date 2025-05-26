package com.postech.fastfood.core.ports;

import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import java.util.UUID;

public interface PaymentRepositoryPort {
    String create(UUID orderId, PaymentRequest paymentRequest);

    String save(UUID orderId);
}
