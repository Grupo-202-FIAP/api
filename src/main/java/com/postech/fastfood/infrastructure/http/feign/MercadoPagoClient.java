package com.postech.fastfood.infrastructure.http.feign;

import com.postech.fastfood.application.usecases.payment.dto.OrderMercadoPagoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "mercadoPagoClient",
        url = "https://api.mercadopago.com/v1/orders"
)
public interface MercadoPagoClient {
    @PostMapping(consumes = "application/json")
    String createOrder(
            @RequestHeader("X-Idempotency-Key") String idempotencyKey,
            @RequestHeader("Authorization") String authorization,
            @RequestBody OrderMercadoPagoRequestDto requestBody
    );
}
