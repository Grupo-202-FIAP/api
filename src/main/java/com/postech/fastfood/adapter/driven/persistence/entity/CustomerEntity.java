package com.postech.fastfood.adapter.driven.persistence.entity;

import java.io.Serial;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tb_customer")
@NoArgsConstructor
public class CustomerEntity extends UserEntity {
    @Serial
    private static final long serialVersionUID = -6156531295790055692L;
    @OneToMany
    List<OrderEntity> listOrderEntities;
}
