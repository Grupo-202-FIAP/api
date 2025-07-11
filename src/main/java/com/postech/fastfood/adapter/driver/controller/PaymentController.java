package com.postech.fastfood.adapter.driver.controller;

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

    public PaymentController(CreatePaymentUseCase createPaymentUseCase,
                             ProccessPaymentUseCase savePayment) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.savePayment = savePayment;
    }

    //    @PostMapping("/create")
    //    public ResponseEntity<String> createPayment(@RequestParam ("orderId") UUID orderId,
    //                                                @Valid @RequestBody PaymentRequest paymentRequest) {
    //        return ResponseEntity.ok(this.createPaymentUseCase.execute(orderId, paymentRequest));
    //    }

    @PostMapping("/pay")
    public ResponseEntity savePayment(@RequestParam ("orderId") UUID orderId) {
        savePayment.execute(orderId);
        return ResponseEntity.ok().build();
    }


}
