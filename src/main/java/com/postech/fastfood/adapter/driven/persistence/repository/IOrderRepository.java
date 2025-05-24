package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "SELECT IDENT_CURRENT('tb_orders')", nativeQuery = true)
    Long getNextIdentifier();
}
