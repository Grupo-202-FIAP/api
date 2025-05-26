package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Order;
import java.util.UUID;

public interface OrderRepositoryPort {
    Order findByOrderId(UUID orderId);
}
