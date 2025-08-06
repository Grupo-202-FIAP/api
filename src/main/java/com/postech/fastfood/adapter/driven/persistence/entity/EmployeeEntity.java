package com.postech.fastfood.adapter.driven.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tb_employee")
@NoArgsConstructor
public class EmployeeEntity extends UserEntity {
    private String password;
}
