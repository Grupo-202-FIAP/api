package com.postech.fastfood.application.usecases.interfaces.payment;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;

public interface CheckPaymentStatusUseCase {
    String execute(@NotEmpty UUID orderId);
}

