package com.postech.fastfood.infrastructure.gateways.order;

import com.postech.fastfood.core.domain.Order;
import java.util.List;

public interface ListOrdersUseCase {
    List<Order> execute();
}
