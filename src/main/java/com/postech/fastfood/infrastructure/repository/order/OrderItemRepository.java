package com.postech.fastfood.adapter.driven.persistence.repository.order;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
