package com.postech.fastfood.adapter.driven.persistence.repository.order;

import java.util.UUID;
import com.postech.fastfood.adapter.driven.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
