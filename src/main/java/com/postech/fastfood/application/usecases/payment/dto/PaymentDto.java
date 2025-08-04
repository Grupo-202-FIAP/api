package com.postech.fastfood.application.usecases.payment.dto;

import lombok.Builder;

@Builder
public record PaymentDto(
        String amount
) {
}
