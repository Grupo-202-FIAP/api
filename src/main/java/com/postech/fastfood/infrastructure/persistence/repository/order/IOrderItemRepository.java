package com.postech.fastfood.infrastructure.persistence.repository.order;

import com.postech.fastfood.infrastructure.persistence.entity.OrderItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
