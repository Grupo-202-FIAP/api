package com.postech.fastfood.infrastructure.gateways.payment;

import java.util.UUID;

public interface ProccessPaymentUseCase {
    void execute(UUID order);
}
