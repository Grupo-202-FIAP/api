package com.postech.fastfood.adapter.driver.controller.dto.response;

import com.postech.fastfood.core.domain.enums.PaymentMethod;
import com.postech.fastfood.core.domain.enums.PaymentStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentResponse(
        UUID id,
        PaymentStatus status,
        PaymentMethod paymentMethod,
        LocalDateTime paymentDateTime
) {
}
