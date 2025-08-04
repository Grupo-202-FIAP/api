package com.postech.fastfood.infrastructure.gateways.payment;

import com.postech.fastfood.infrastructure.webhook.dao.WebhookEvent;

public interface ProcessPaymentNotificationUseCase {
    void execute(WebhookEvent webhookEvent, String signature, String requestId, String dataIdParam);
}
