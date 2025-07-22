package com.postech.fastfood.infrastructure.repository.order;

import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.infrastructure.repository.entity.OrderEntity;
import com.postech.fastfood.infrastructure.repository.product.IProductRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final IOrderEntityRepository orderEntityRepository;
    private final ICustomerEntityRepository customerEntityRepository;
    private final IProductRepository productRepository;
    private final IOrderItemRepository orderItemRepository;

    public OrderRepositoryAdapter(IOrderEntityRepository orderEntityRepository, ICustomerEntityRepository customerEntityRepository,
                                  IProductRepository productRepository, IOrderItemRepository orderItemRepository) {
        this.orderEntityRepository = orderEntityRepository;
        this.customerEntityRepository = customerEntityRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order findById(UUID orderId) {
        final Order order;
        final OrderEntity orderEntity = this.orderEntityRepository.findById(orderId)
                .orElseThrow(() -> new FastFoodException("Order not found with id:" + orderId, "Order Not Found", HttpStatus.NOT_FOUND));
        order = OrderMapper.toDomain(orderEntity);
        return order;
    }

    @Override
    public List<Order> findAll() {
        final List<Order> orderList = this.orderEntityRepository.findAll().stream().map(OrderMapper::toDomain).collect(Collectors.toList());
        if (orderList.isEmpty()) {
            throw new FastFoodException("Nenhum pedido encontrado", "Não há pedidos registrados no sistema", HttpStatus.NOT_FOUND);
        }
        return orderList;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        final List<Order> orderList =
                this.orderEntityRepository.findByOrderStatus(status).stream().map(OrderMapper::toDomain).collect(Collectors.toList());
        if (orderList.isEmpty()) {
            throw new FastFoodException("Nenhum pedido encontrado com o status: " + status,
                    "Não há pedidos com o status " + status + " registrados no sistema", HttpStatus.NOT_FOUND);
        }
        return orderList;
    }

    @Override
    public Order save(Order order) {
        final OrderEntity entity = OrderMapper.toEntity(order);
        final OrderEntity save = orderEntityRepository.save(entity);
        return OrderMapper.toDomain(save);
    }
}
