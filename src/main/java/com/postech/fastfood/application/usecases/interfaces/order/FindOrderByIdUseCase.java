package com.postech.fastfood.application.usecases.interfaces.order;

import com.postech.fastfood.domain.Order;
import java.util.UUID;

public interface FindOrderByIdUseCase {
    Order execute(UUID orderId);
}
