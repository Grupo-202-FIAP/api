package com.postech.fastfood.adapter.driven.persistence.repository.order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.OrderEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.OrderItemEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.customer.ICustomerEntityRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.product.IProductRepository;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.application.mapper.OrderMapper;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.OrderStatus;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final IOrderEntityRepository orderEntityRepository;
    private final ICustomerEntityRepository customerEntityRepository;
    private final IProductRepository productRepository;
    private final IOrderItemRepository iOrderItemRepository;

    public OrderRepositoryAdapter(
            IOrderEntityRepository orderEntityRepository,
            ICustomerEntityRepository customerEntityRepository, IProductRepository productRepository, IOrderItemRepository iOrderItemRepository) {
        this.orderEntityRepository = orderEntityRepository;
        this.customerEntityRepository = customerEntityRepository;
        this.productRepository = productRepository;
        this.iOrderItemRepository = iOrderItemRepository;
    }

    @Override
    public Order findById(UUID orderId) {
        Order order = null;
        final OrderEntity orderEntity = this.orderEntityRepository
                .findById(orderId)
                .orElseThrow(() -> new FastFoodException(
                        "Order not found with id:" + orderId,
                        "Order Not Found",
                        HttpStatus.NOT_FOUND
                ));
        order = OrderMapper.toDomain(orderEntity);
        return order;
    }

    @Override
    public List<Order> findAll() {
        final List<Order> orderList = this.orderEntityRepository.findAll().stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());

        if (orderList.isEmpty()) {
            throw new FastFoodException(
                    "Nenhum pedido encontrado",
                    "Não há pedidos registrados no sistema",
                    HttpStatus.NOT_FOUND
            );
        }
        return orderList;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        final List<Order> orderList = this.orderEntityRepository.findByOrderStatus(status).stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());

        if (orderList.isEmpty()) {
            throw new FastFoodException(
                    "Nenhum pedido encontrado com o status: " + status,
                    "Não há pedidos com o status " + status + " registrados no sistema",
                    HttpStatus.NOT_FOUND
            );
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
