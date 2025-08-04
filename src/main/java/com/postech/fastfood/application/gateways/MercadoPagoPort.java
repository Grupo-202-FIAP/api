package com.postech.fastfood.application.gateways;

import com.postech.fastfood.application.usecases.payment.dto.OrderMercadoPagoRequestDto;

public interface MercadoPagoPort {
    String createOrder(String idempotencyKey, String accessToken, OrderMercadoPagoRequestDto requestBody,String orderId);
}
