package com.postech.fastfood.adapter.driver.controller.dto.request;

import com.postech.fastfood.core.domain.enums.PaymentMethod;
import jakarta.persistence.Enumerated;

public record PaymentRequest(
        @Enumerated
        PaymentMethod paymentMethod
) {
}
