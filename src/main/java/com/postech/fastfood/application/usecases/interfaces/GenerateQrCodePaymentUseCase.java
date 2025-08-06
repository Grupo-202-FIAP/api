package com.postech.fastfood.application.usecases.interfaces;

import java.util.UUID;

public interface GenerateQrCodePaymentUseCase {
    String execute(UUID orderId);
}
