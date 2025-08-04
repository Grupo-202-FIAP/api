package com.postech.fastfood.infrastructure.http.mercadopago.security;

import com.postech.fastfood.application.gateways.LoggerPort;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoWebhookSignatureValidator {

    private final LoggerPort loggerPort;

    @Value("${mercadoPago.webhook.secretKey}")
    private String secretKey;

    public MercadoPagoWebhookSignatureValidator(LoggerPort loggerPort) {
        this.loggerPort = loggerPort;
    }

    public Boolean isValid(String idUrl, String xRequestId, String xSignature) {

        try {
            String ts = xSignature.split(",")[0].split("=")[1];
            String expectedSignature = xSignature.split(",")[1].split("=")[1]; // valor do v1

            String signedTemplate = String.format("id:%s;request-id:%s;ts:%s;", idUrl, xRequestId, ts);

            String generatedSignature = new HmacUtils("HmacSHA256", secretKey).hmacHex(signedTemplate);

            return generatedSignature.equals(expectedSignature);

        } catch (Exception e) {
            loggerPort.error("[Webhook][Payment] Error validating signature: {}", e.getMessage());
            return false;
        }
    }

}
