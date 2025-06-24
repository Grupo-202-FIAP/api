package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.OrderRequest;
import com.postech.fastfood.adapter.driver.controller.dto.response.OrderResponse;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersUseCase;
import com.postech.fastfood.core.usecase.order.UpdateOrderStatusUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final UpdateOrderStatusUseCase updateOrderUseCase;
    private final LoggerPort logger;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           ListOrdersUseCase listOrdersUseCase,
                           ListOrdersByStatusUseCase listOrdersByStatusUseCase,
                           UpdateOrderStatusUseCase updateOrderUseCase,
                           LoggerPort logger) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
        this.listOrdersByStatusUseCase = listOrdersByStatusUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.logger = logger;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        logger.info("[Order] Iniciando criação de pedido");
        Order domain = OrderMapper.toDomain(orderRequest);
        domain = this.createOrderUseCase.execute(domain);
        final OrderResponse response = OrderMapper.toResponse(domain);
        logger.info("[Order] Pedido criado com sucesso: id={}", domain.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> listOrders() {
        logger.info("[Order] Buscando todos os pedidos");
        final List<Order> orders = this.listOrdersUseCase.execute();
        final List<OrderResponse> response = orders.stream()
                .map(OrderMapper::toResponse)
                .toList();
        logger.info("[Order] {} pedidos encontrados", orders.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<List<OrderResponse>> listOrderByStatus(@Valid @RequestParam("status") OrderStatus status) {
        logger.info("[Order] Buscando pedidos com status={}", status);
        final List<Order> orders = this.listOrdersByStatusUseCase.execute(status);
        final List<OrderResponse> response = orders.stream()
                .map(OrderMapper::toResponse)
                .toList();
        logger.info("[Order] {} pedidos encontrados com status={}", orders.size(), status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/status")
    public ResponseEntity<OrderResponse> updateStatus(@RequestParam UUID orderID) {
        logger.info("[Order] Atualizando status do pedido com id={}", orderID);
        final Order order = updateOrderUseCase.execute(orderID);
        final OrderResponse response = OrderMapper.toResponse(order);
        logger.info("[Order] Status do pedido atualizado: id={}, novoStatus={}", order.getId(), order.getStatus());
        return ResponseEntity.ok().body(response);
    }

}
