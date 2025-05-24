package com.postech.fastfood.adapter.driven.persistence.entity;

import java.math.BigDecimal;
import java.util.List;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.domain.enums.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_orders")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String identifier;
    @ManyToOne
    private CustomerEntity customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> itens;
    private BigDecimal totalPrice;
    @Enumerated
    private PaymentStatus paymentStatus;
    @Enumerated
    private OrderStatus orderStatus;
}
