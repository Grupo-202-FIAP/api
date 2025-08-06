package com.postech.fastfood.core.usecase.payment;

import java.util.UUID;

public interface ProccessPaymentUseCase {
    void execute(UUID order);
}
