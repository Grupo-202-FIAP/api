package com.postech.fastfood.core.usecase.order;

import com.postech.fastfood.core.domain.Order;
import java.util.List;

public interface ListOrdersUseCase {
    List<Order> execute();
}
