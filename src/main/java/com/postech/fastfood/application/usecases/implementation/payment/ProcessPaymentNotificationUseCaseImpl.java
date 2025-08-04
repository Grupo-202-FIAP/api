package com.postech.fastfood.application.usecases.implementation.payment;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.application.usecases.interfaces.order.UpdateOrderStatusUseCase;
import com.postech.fastfood.application.usecases.interfaces.payment.ProcessPaymentNotificationUseCase;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.domain.enums.PaymentStatus;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.http.mercadopago.security.MercadoPagoWebhookSignatureValidator;
import com.postech.fastfood.infrastructure.webhook.dao.WebhookEvent;
import org.springframework.http.HttpStatus;

public class ProcessPaymentNotificationUseCaseImpl implements ProcessPaymentNotificationUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final MercadoPagoWebhookSignatureValidator mercadoPagoWebhookSignatureValidator;
    private final LoggerPort logger;

    public ProcessPaymentNotificationUseCaseImpl(OrderRepositoryPort orderRepositoryPort, UpdateOrderStatusUseCase updateOrderStatusUseCase, MercadoPagoWebhookSignatureValidator mercadoPagoWebhookSignatureValidator, LoggerPort logger) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.mercadoPagoWebhookSignatureValidator = mercadoPagoWebhookSignatureValidator;
        this.logger = logger;
    }

    @Override
    public void execute(WebhookEvent event, String signature, String requestId, String dataIdParam) {
        if (signature == null) {
            logger.error("[Webhook][Payment] Signature header is missing in the webhook event: {}", event);
            throw new FastFoodException("Signature header is missing in the webhook event", "Signature Header Missing", HttpStatus.BAD_REQUEST);
        }
        if (!mercadoPagoWebhookSignatureValidator.isValid(event.getData().getId(), requestId, signature)) {
            logger.error("[Webhook][Payment] Invalid signature for webhook event: {}", event);
            throw new FastFoodException("Invalid signature for webhook event", "Invalid Signature", HttpStatus.UNAUTHORIZED);
        }

        Order order = orderRepositoryPort.findByIdentifier(event.getData().getExternalReference());
        if (order == null) {
            logger.warn("[Webhook][Payment] No order found for webhook event: {}", event);
            throw new FastFoodException("No order found for webhook event", "Order Not Found", HttpStatus.NOT_FOUND);
        }
        switch (event.getAction()) {
            case "order.processed":
                processOrderSucess(order);
                break;
            case "order.expired":
                processOrderExpired(order);
                break;
            default:
                logger.warn("[Webhook][Payment] Unknown action '{}' for webhook event: {}", event.getAction(), event);
        }
    }

    private void processOrderExpired(Order expiredOrder) {
        logger.info("[Webhook][Payment] Order expired for webhook order: {}", expiredOrder);
        expiredOrder.getPayment().setStatus(PaymentStatus.CANCELLED);
        expiredOrder.setStatus(OrderStatus.CANCELLED);
        orderRepositoryPort.save(expiredOrder);
        logger.info("[Webhook][Payment] Order updated to cancelled status:: {}", expiredOrder.getId());

    }

    private void processOrderSucess(Order order) {
        order.getPayment().setStatus(PaymentStatus.AUTHORIZED);
        Order updatedOrder = orderRepositoryPort.save(order);
        updateOrderStatusUseCase.execute(updatedOrder.getId());
        logger.info("[Webhook][Payment] Order updated to authorized status: {}", updatedOrder.getId());
    }

}
