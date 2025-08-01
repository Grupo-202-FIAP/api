package com.postech.fastfood.core.usecase.order;

import com.postech.fastfood.core.domain.Order;
import java.util.UUID;

public interface FindOrderByIdUseCase {
    Order execute(UUID orderId);
}
