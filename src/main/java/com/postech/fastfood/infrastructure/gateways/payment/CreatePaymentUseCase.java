package com.postech.fastfood.core.usecase.payment;

import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import java.util.UUID;

public interface CreatePaymentUseCase {
    String execute(UUID orderId, PaymentRequest paymentRequest);
}
