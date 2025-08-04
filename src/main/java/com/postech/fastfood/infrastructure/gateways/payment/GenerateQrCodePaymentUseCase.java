package com.postech.fastfood.infrastructure.gateways.payment;

import java.util.UUID;

public interface GenerateQrCodePaymentUseCase {
    String execute(UUID orderId);
}
