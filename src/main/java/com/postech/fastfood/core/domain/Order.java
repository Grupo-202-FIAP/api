package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private UUID id;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private UUID userId;
    private UUID paymentId;

    public Order(UUID id, BigDecimal totalPrice, OrderStatus status, LocalDateTime orderDateTime, UUID userId, UUID paymentId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDateTime = orderDateTime;
        this.userId = userId;
        this.paymentId = paymentId;
    }

    public Order() {
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.orderDateTime = builder.orderDateTime;
        this.userId = builder.userId;
        this.paymentId = builder.paymentId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public static class Builder {
        private UUID id;
        private BigDecimal totalPrice;
        private OrderStatus status;
        private LocalDateTime orderDateTime;
        private UUID userId;
        private UUID paymentId;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder orderDateTime(LocalDateTime orderDateTime) {
            this.orderDateTime = orderDateTime;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder paymentId(UUID paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Order build() {
         return new Order(this);
        }
    }

}
