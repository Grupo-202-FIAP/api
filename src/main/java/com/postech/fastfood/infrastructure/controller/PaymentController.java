package com.postech.fastfood.infrastructure.controller;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.infrastructure.gateways.payment.CreatePaymentUseCase;
import com.postech.fastfood.infrastructure.gateways.payment.GenerateQrCodePaymentUseCase;
import com.postech.fastfood.infrastructure.gateways.payment.ProccessPaymentUseCase;
import java.util.UUID;
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

    public PaymentController(
            GenerateQrCodePaymentUseCase generateQrCodePaymentUseCase, LoggerPort logger) {
        this.generateQrCodePaymentUseCase = generateQrCodePaymentUseCase;

        this.logger = logger;
    }

    //    @PostMapping("/create")
    //    public ResponseEntity<String> createPayment(@RequestParam ("orderId") UUID orderId,
    //                                                @Valid @RequestBody PaymentRequest paymentRequest) {
    //        return ResponseEntity.ok(this.createPaymentUseCase.execute(orderId, paymentRequest));
    //    }

//    @PostMapping("/pay")
//    public ResponseEntity<String> savePayment(@RequestParam ("orderId") UUID orderId) {
//        logger.info("[Payment] Iniciando processamento de pagamento para pedido id={}", orderId);
//        savePayment.execute(orderId);
//        logger.info("[Payment] Pagamento processado com sucesso para pedido id={}", orderId);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/generate=qr-code")
    public ResponseEntity<String> generateQrCode(@RequestParam("orderId") UUID orderId) {
        logger.info("[Payment] Iniciando geração de QR Code para o pedido id={}", orderId);
        String qrCode = generateQrCodePaymentUseCase.execute(orderId);
        logger.info("[Payment] QR Code gerado com sucesso para o pedido id={}", orderId);
        return ResponseEntity.ok(qrCode);
    }


}
