package com.postech.fastfood.application.usecases.interfaces.payment;

import com.postech.fastfood.infrastructure.webhook.dao.WebhookEvent;

public interface ProcessPaymentNotificationUseCase {
    void execute(WebhookEvent webhookEvent, String signature, String requestId, String dataIdParam);
}
