package com.postech.fastfood.infrastructure.http.mercadopago.dto;

import lombok.Builder;

@Builder
public record ConfigDto(
        QrConfigDto qr
) {
}



