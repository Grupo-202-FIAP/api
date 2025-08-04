package com.postech.fastfood.infrastructure.http;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.gateways.MercadoPagoGateway;
import com.postech.fastfood.application.usecases.payment.dto.OrderMercadoPagoRequestDto;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.http.feign.MercadoPagoClient;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoGatewayImpl implements MercadoPagoGateway {

    private final MercadoPagoClient mercadoPagoClient;
    private final LoggerPort logger;

    public MercadoPagoGatewayImpl(MercadoPagoClient mercadoPagoClient, LoggerPort logger) {
        this.mercadoPagoClient = mercadoPagoClient;
        this.logger = logger;
    }

    @Override
    public String createOrder(String idempotencyKey, String accessToken, OrderMercadoPagoRequestDto requestBody,String orderId) {
        try {
            String resposta = mercadoPagoClient.createOrder(idempotencyKey, "Bearer " + accessToken, requestBody );
            logger.info("[Service][Payment] Resposta MercadoPago: {}", resposta);
            return resposta;

        } catch (FeignException e) {
            logger.warn("[Service][Payment] Erro MercadoPago: {}", e.getMessage());
            throw new FastFoodException("Erro ao gerar QR Code de pagamento",
                    "Não foi possível gerar o QR Code de pagamento para o pedido: " + orderId,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("[Service][Payment] Erro inesperado MercadoPago: {}", e.getMessage());
            throw new FastFoodException("Erro ao gerar QR Code de pagamento",
                    "Não foi possível gerar o QR Code de pagamento para o pedido: " + orderId,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
