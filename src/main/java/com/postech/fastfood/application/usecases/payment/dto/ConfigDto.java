package com.postech.fastfood.application.usecases.payment.dto;

import lombok.Builder;

@Builder
public record ConfigDto(
        QrConfigDto qr
) {
}



