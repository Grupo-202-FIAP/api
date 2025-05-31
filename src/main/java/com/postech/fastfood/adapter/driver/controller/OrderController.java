package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.OrderRequest;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersUseCase;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final ListOrdersByStatusUseCase listOrdersByStatusUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           ListOrdersUseCase listOrdersUseCase,
                           ListOrdersByStatusUseCase listOrdersByStatusUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
        this.listOrdersByStatusUseCase = listOrdersByStatusUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        final Order domain = OrderMapper.toDomain(orderRequest);
        return ResponseEntity.ok(this.createOrderUseCase.execute(domain));
    }

    @GetMapping()
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(this.listOrdersUseCase.execute());
    }

    @GetMapping("/status")
    public ResponseEntity<List<Order>> listOrderByStatus(@Valid @RequestParam ("status") OrderStatus status) {
        return ResponseEntity.ok(this.listOrdersByStatusUseCase.execute(status));
    }
}
