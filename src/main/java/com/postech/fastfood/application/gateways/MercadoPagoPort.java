package com.postech.fastfood.application.gateways;

import com.postech.fastfood.infrastructure.http.mercadopago.dto.OrderMercadoPagoRequestDto;

public interface MercadoPagoPort {
    String createOrder(String idempotencyKey, String accessToken, OrderMercadoPagoRequestDto requestBody, String orderId);
}
