package com.postech.fastfood.application.usecases.implementation.payment;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.gateways.MercadoPagoPort;
import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.application.usecases.interfaces.GenerateQrCodePaymentUseCase;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.http.mercadopago.dto.OrderMercadoPagoRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import java.util.UUID;

public class GenerateQrCodePaymentUseCaseImpl implements GenerateQrCodePaymentUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final String externalPosId = "TOTEMFASTFOOD";
    private final String qrCodeModeType = "dynamic";
    private final MercadoPagoPort mercadoPagoPort;
    private final LoggerPort logger;
    @Value("${mercadoPago.accessToken}")
    private String accessToken;

    public GenerateQrCodePaymentUseCaseImpl(OrderRepositoryPort orderRepositoryPort, MercadoPagoPort mercadoPagoPort, LoggerPort logger) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.mercadoPagoPort = mercadoPagoPort;
        this.logger = logger;
    }


    public String execute(UUID orderId) {
        final Order order = orderRepositoryPort.findById(orderId);
        if (order == null) {
            throw new FastFoodException("Pedido não encontrado",
                    "Não foi possível encontrar o pedido com ID: " + orderId,
                    HttpStatus.NOT_FOUND);
        }
        logger.info("[Service][Payment] Criando Order no MercadoPago para o pedido: {}", orderId);
         final OrderMercadoPagoRequestDto requestBody = OrderMapper.toMercadoPagoV1OrderRequest(order, externalPosId, qrCodeModeType);

        final String idempotencyKey = UUID.randomUUID().toString();
        return mercadoPagoPort.createOrder(idempotencyKey, accessToken, requestBody,orderId.toString());
    }
}
