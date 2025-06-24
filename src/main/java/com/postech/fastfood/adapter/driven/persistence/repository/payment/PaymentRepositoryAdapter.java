package com.postech.fastfood.adapter.driven.persistence.repository.payment;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.order.IOrderEntityRepository;
import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.domain.enums.PaymentStatus;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final IPaymentEntityRepository paymentEntityRepository;
    private final IOrderEntityRepository orderEntityRepository;
    private final LoggerPort logger;

    public PaymentRepositoryAdapter(
            IPaymentEntityRepository paymentEntityRepository,
            IOrderEntityRepository orderEntityRepository,
            LoggerPort logger) {
        this.paymentEntityRepository = paymentEntityRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.logger = logger;
    }

    @Override
    public String create(UUID orderId, PaymentRequest paymentRequest) {
        //        final OrderEntity orderEntity = getOrderById(orderId);
        //
        //        if (paymentRequest.paymentMethod() != PaymentMethod.QR_CODE) {
        //            throw new FastFoodException(
        //                    "Method not accepted" + paymentRequest.paymentMethod(),
        //                    "Method not accepted",
        //                    HttpStatus.BAD_REQUEST
        //            );
        //        }
        //
        //        validatePaymentStatus(orderEntity.getPayment(), orderId);
        //
        //        orderEntity.getPayment().setStatus(PaymentStatus.PENDING);
        //        orderEntity.getPayment().setPaymentMethod(paymentRequest.paymentMethod());

        return "Payment Created";
    }

    @Override
    public String save(UUID orderId) {
        logger.info("[Repository][Payment] Processando pagamento para pedido id={}", orderId);

        final OrderEntity orderEntity = getOrderById(orderId);

        validatePaymentStatus(orderEntity.getPayment(), orderId);
        orderEntity.setOrderStatus(OrderStatus.RECEIVED);
        orderEntity.getPayment().setStatus(PaymentStatus.AUTHORIZED);

        this.orderEntityRepository.save(orderEntity);

        logger.info("[Repository][Payment] Pagamento autorizado e status do pedido atualizado: id={}, status={}",
                orderId, orderEntity.getOrderStatus());

        return "Payment Realized";
    }


    private OrderEntity getOrderById(UUID orderId) {
        logger.debug("[Repository][Payment] Buscando pedido por id={}", orderId);

        return orderEntityRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Payment] Pedido não encontrado: id={}", orderId);

                    return new FastFoodException(
                            "Order not found for ID: " + orderId,
                            "Order Not Found",
                            HttpStatus.NOT_FOUND
                    );
                });
    }

    private void validatePaymentStatus(PaymentEntity payment, UUID orderId) {
        logger.debug("[Repository][Payment] Validando status atual do pagamento para pedido id={}", orderId);

        if (payment.getStatus() == PaymentStatus.CANCELLED || payment.getStatus() == PaymentStatus.APPROVED) {
            logger.warn("[Repository][Payment] Pagamento já processado anteriormente: status={}, orderId={}",
                    payment.getStatus(), orderId);

            throw new FastFoodException(
                    "Payment already exists for Order ID: " + orderId,
                    "Payment Already Exists",
                    HttpStatus.CONFLICT
            );
        }
    }
}
