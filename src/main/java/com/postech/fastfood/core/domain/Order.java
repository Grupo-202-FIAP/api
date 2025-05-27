package com.postech.fastfood.core.domain;

import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private String identifier;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private Customer customer;
    private Payment payment;
    private List<OrderItem> itens;
    private LocalDateTime updatedAt;

    public Order(
            Long id,
            String identifier, BigDecimal totalPrice, OrderStatus status, LocalDateTime orderDateTime, Customer customer, Payment payment,
            List<OrderItem> itens, LocalDateTime updatedAt) {
        this.id = id;
        this.identifier = identifier;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDateTime = orderDateTime;
        this.customer = customer;
        this.payment = payment;
        this.itens = itens;
        this.updatedAt = updatedAt;
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
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class Builder {
        private Long id;
        private BigDecimal totalPrice;
        private OrderStatus status;
        private LocalDateTime orderDateTime;
        private Customer customer;
        private Payment payment;
        private List<OrderItem> itens;
        private String identifier;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
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

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public BigDecimal calculateTotalPrice() {
        if (itens == null || itens.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return itens.stream()
                .map(item -> item.getProduct().getUnitPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

}
