package com.postech.fastfood.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.postech.fastfood.core.domain.enums.OrderStatus;

public class Order {
    // TODO CHANGE TO LONG
    private UUID id;
    private String identifier;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private Customer customer;
    private Payment payment;
    private List<OrderItem> itens;

    public Order(UUID id, String identifier, BigDecimal totalPrice, OrderStatus status, LocalDateTime orderDateTime, Customer customer, Payment payment, List<OrderItem> itens) {
        this.id = id;
        this.identifier = identifier;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDateTime = orderDateTime;
        this.customer = customer;
        this.payment = payment;
        this.itens = itens;
    }

    public Order() {
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.orderDateTime = builder.orderDateTime;
        this.customer = builder.customer;
        this.payment = builder.payment;
        this.itens = builder.itens;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public static class Builder {
        private UUID id;
        private BigDecimal totalPrice;
        private OrderStatus status;
        private LocalDateTime orderDateTime;
        private Customer customer;
        private Payment payment;
        private List<OrderItem> itens;

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

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder itens(List<OrderItem> itens) {
            this.itens = itens;
            return this;
        }

        public Builder payment(Payment payment) {
            this.payment = payment;
            return this;
        }


        public Order build() {
            return new Order(this);
        }
    }

}
