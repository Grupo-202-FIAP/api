package com.postech.fastfood.adapter.driven.persistence.repository.order;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderEntityRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByOrderStatus(OrderStatus status);
}
