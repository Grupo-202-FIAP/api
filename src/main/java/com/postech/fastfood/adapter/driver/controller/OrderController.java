package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.OrderRequest;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersUseCase;
import com.postech.fastfood.core.usecase.order.UpdateOrderStatusUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final ListOrdersByStatusUseCase listOrdersByStatusUseCase;
    private final UpdateOrderStatusUseCase updateOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           ListOrdersUseCase listOrdersUseCase,
                           ListOrdersByStatusUseCase listOrdersByStatusUseCase, UpdateOrderStatusUseCase updateOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
        this.listOrdersByStatusUseCase = listOrdersByStatusUseCase;

        this.updateOrderUseCase = updateOrderUseCase;
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

    @PutMapping("/status")
    public ResponseEntity updateStatus(@RequestParam UUID orderID){
            updateOrderUseCase.execute(orderID);
        return ResponseEntity.ok().build();
    }

}
