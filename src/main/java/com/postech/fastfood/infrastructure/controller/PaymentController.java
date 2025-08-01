package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.usecase.payment.CreatePaymentUseCase;
import com.postech.fastfood.core.usecase.payment.ProccessPaymentUseCase;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final ProccessPaymentUseCase savePayment;
    private final LoggerPort logger;

    public PaymentController(CreatePaymentUseCase createPaymentUseCase,
                             ProccessPaymentUseCase savePayment,
                             LoggerPort logger) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.savePayment = savePayment;
        this.logger = logger;
    }

    //    @PostMapping("/create")
    //    public ResponseEntity<String> createPayment(@RequestParam ("orderId") UUID orderId,
    //                                                @Valid @RequestBody PaymentRequest paymentRequest) {
    //        return ResponseEntity.ok(this.createPaymentUseCase.execute(orderId, paymentRequest));
    //    }

    @PostMapping("/pay")
    public ResponseEntity<String> savePayment(@RequestParam ("orderId") UUID orderId) {
        logger.info("[Payment] Iniciando processamento de pagamento para pedido id={}", orderId);
        savePayment.execute(orderId);
        logger.info("[Payment] Pagamento processado com sucesso para pedido id={}", orderId);
        return ResponseEntity.ok().build();
    }


}
