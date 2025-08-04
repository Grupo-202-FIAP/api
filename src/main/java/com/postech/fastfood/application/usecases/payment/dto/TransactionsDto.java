package com.postech.fastfood.application.usecases.payment.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TransactionsDto(
        List<PaymentDto> payments
) {
}




