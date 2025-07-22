package com.postech.fastfood.infrastructure.repository.order;

import com.postech.fastfood.infrastructure.repository.entity.OrderItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
