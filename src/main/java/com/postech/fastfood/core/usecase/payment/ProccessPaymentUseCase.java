package com.postech.fastfood.core.usecase.payment;

import java.util.UUID;

public interface ProccessPaymentUseCase {
    String execute(UUID order);
}
