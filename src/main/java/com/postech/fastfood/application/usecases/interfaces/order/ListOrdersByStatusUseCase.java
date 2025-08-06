package com.postech.fastfood.application.usecases.interfaces.order;

import com.postech.fastfood.domain.Order;
import java.util.List;

public interface ListOrdersByStatusUseCase {
    List<Order> execute( );
}
