package com.postech.fastfood.adapter.driven.persistence.repository.payment;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.order.IOrderEntityRepository;
import com.postech.fastfood.adapter.driver.controller.dto.request.PaymentRequest;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.domain.enums.PaymentMethod;
import com.postech.fastfood.core.domain.enums.PaymentStatus;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import java.util.UUID;

import com.postech.fastfood.core.service.order.UpdateOrderStatusUseCaseImpl;
import com.postech.fastfood.core.usecase.order.UpdateOrderStatusUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final IPaymentEntityRepository paymentEntityRepository;
    private final IOrderEntityRepository orderEntityRepository;

    public PaymentRepositoryAdapter(IPaymentEntityRepository paymentEntityRepository, IOrderEntityRepository orderEntityRepository) {
        this.paymentEntityRepository = paymentEntityRepository;
        this.orderEntityRepository = orderEntityRepository;
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
        final OrderEntity orderEntity = getOrderById(orderId);

        validatePaymentStatus(orderEntity.getPayment(), orderId);
        orderEntity.setOrderStatus(OrderStatus.RECEIVED);
        orderEntity.getPayment().setStatus(PaymentStatus.AUTHORIZED);

        this.orderEntityRepository.save(orderEntity);

        return "Payment Realized";
    }


    private OrderEntity getOrderById(UUID orderId) {
        return orderEntityRepository.findById(orderId)
                .orElseThrow(() -> new FastFoodException(
                        "Order not found for ID: " + orderId,
                        "Order Not Found",
                        HttpStatus.NOT_FOUND
                ));
    }

    private void validatePaymentStatus(PaymentEntity payment, UUID orderId) {
        if (payment.getStatus() == PaymentStatus.CANCELLED || payment.getStatus() == PaymentStatus.APPROVED) {
            throw new FastFoodException(
                    "Payment already exists for Order ID: " + orderId,
                    "Payment Already Exists",
                    HttpStatus.CONFLICT
            );
        }
    }
}
