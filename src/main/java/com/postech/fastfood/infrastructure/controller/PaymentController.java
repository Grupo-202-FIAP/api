package com.postech.fastfood.infrastructure.controller;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.usecases.interfaces.GenerateQrCodePaymentUseCase;
import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final GenerateQrCodePaymentUseCase generateQrCodePaymentUseCase;
    private final LoggerPort logger;

    public PaymentController(GenerateQrCodePaymentUseCase generateQrCodePaymentUseCase, LoggerPort logger) {
        this.generateQrCodePaymentUseCase = generateQrCodePaymentUseCase;
        this.logger = logger;
    }


    @PostMapping("/generate=qr-code")
    public ResponseEntity<String> generateQrCode(@RequestParam("orderId") @NotEmpty UUID orderId) {
        logger.info("[Payment] Iniciando geração de QR Code para o pedido id={}", orderId);
        final String qrCode = generateQrCodePaymentUseCase.execute(orderId);
        logger.info("[Payment] QR Code gerado com sucesso para o pedido id={}", orderId);
        return ResponseEntity.ok(qrCode);
    }


}
