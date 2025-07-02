package com.postech.fastfood.adapter.driven.persistence.repository.order;

import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.product.IProductRepository;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final IOrderEntityRepository orderEntityRepository;
    private final ICustomerEntityRepository customerEntityRepository;
    private final IProductRepository productRepository;
    private final IOrderItemRepository orderItemRepository;
    private final LoggerPort logger;

    public OrderRepositoryAdapter(
            IOrderEntityRepository orderEntityRepository,
            ICustomerEntityRepository customerEntityRepository,
            IProductRepository productRepository,
            IOrderItemRepository orderItemRepository,
            LoggerPort logger) {
        this.orderEntityRepository = orderEntityRepository;
        this.customerEntityRepository = customerEntityRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.logger = logger;
    }

    @Override
    public Order findById(UUID orderId) {
        logger.info("[Repository][Order] Buscando pedido por id={}", orderId);

        Order order = null;
        final OrderEntity orderEntity = this.orderEntityRepository
                .findById(orderId)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Order] Pedido não encontrado: id={}", orderId);

                    return new FastFoodException(
                            "Order not found with id:" + orderId,
                            "Order Not Found",
                            HttpStatus.NOT_FOUND
                    );
                });
        order = OrderMapper.toDomain(orderEntity);
        return order;
    }

    @Override
    public List<Order> findAll() {
        logger.info("[Repository][Order] Buscando todos os pedidos");

        final List<Order> orderList = this.orderEntityRepository.findAll().stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());

        if (orderList.isEmpty()) {
            logger.warn("[Repository][Order] Nenhum pedido encontrado");

            throw new FastFoodException(
                    "Nenhum pedido encontrado",
                    "Não há pedidos registrados no sistema",
                    HttpStatus.NOT_FOUND
            );
        }

        logger.info("[Repository][Order] {} pedidos encontrados", orderList.size());
        return orderList;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        logger.info("[Repository][Order] Buscando pedidos com status={}", status);

        final List<Order> orderList = this.orderEntityRepository.findByOrderStatus(status).stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());

        if (orderList.isEmpty()) {
            logger.warn("[Repository][Order] Nenhum pedido encontrado com status={}", status);

            throw new FastFoodException(
                    "Nenhum pedido encontrado com o status: " + status,
                    "Não há pedidos com o status " + status + " registrados no sistema",
                    HttpStatus.NOT_FOUND
            );
        }

        logger.info("[Repository][Order] {} pedidos encontrados com status={}", orderList.size(), status);
        return orderList;
    }

    @Override
    public Order save(Order order) {
        logger.info("[Repository][Order] Salvando novo pedido");
        final OrderEntity entity = OrderMapper.toEntity(order);
        final OrderEntity save = orderEntityRepository.save(entity);
        logger.info("[Repository][Order] Pedido salvo com sucesso: id={}", save.getId());
        return OrderMapper.toDomain(save);
    }
}
