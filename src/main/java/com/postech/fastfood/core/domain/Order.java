package com.postech.fastfood.core.domain;

import java.math.BigDecimal;
import java.util.List;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.domain.enums.PaymentStatus;

public class Order {
    private Long id;
    private String identifier;
    private Customer customer;
    private List<OrderItem> itens;
    private BigDecimal totalPrice;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;


    public Order(Long id, String identifier, Customer customer, List<OrderItem> itens, BigDecimal totalPrice, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.id = id;
        this.identifier = identifier;
        this.customer = customer;
        this.itens = itens;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.identifier = builder.identifier;
        this.itens = builder.itens;
        this.totalPrice = builder.totalPrice;
        this.paymentStatus = builder.paymentStatus;
        this.orderStatus = builder.orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<OrderItem> getitens() {
        return itens;
    }

    public void setitens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static class Builder {
        private Long id;
        private Customer customer;
        private String identifier;
        private List<OrderItem> itens;
        private BigDecimal totalPrice;
        private PaymentStatus paymentStatus;
        private OrderStatus orderStatus;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder itens(List<OrderItem> itens) {
            this.itens = itens;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }
}
