package com.postech.fastfood.application.usecases.payment.dto;

import lombok.Builder;

@Builder
public record QrConfigDto(
        String external_pos_id,
        String mode
) {
}
