package com.postech.fastfood.infrastructure.repository.order;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
