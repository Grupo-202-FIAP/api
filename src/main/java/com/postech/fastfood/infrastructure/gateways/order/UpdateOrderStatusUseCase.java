package com.postech.fastfood.infrastructure.gateways.order;

import com.postech.fastfood.domain.Order;
import java.util.UUID;

public interface UpdateOrderStatusUseCase {
    Order execute(UUID orderID);
}
