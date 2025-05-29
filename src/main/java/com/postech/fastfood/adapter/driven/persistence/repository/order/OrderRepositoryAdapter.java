package com.postech.fastfood.adapter.driven.persistence.repository.order;

import java.util.List;
import java.util.Map;
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
    public Order save(Order order) {

        final CustomerEntity customerEntity = customerEntityRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new FastFoodException(
                        "Cliente não encontrado",
                        "Cliente com ID " + order.getCustomer().getId() + " não encontrado",
                        HttpStatus.NOT_FOUND
                ));

        order.setCustomer(CustomerMapper.toDomain(customerEntity));

        final List<Long> productIds = order.getItens()
                .stream()
                .map(orderItem -> orderItem.getProduct().getId())
                .toList();

        final List<Product> products = productRepository.findAllById(productIds).stream().map(ProductMapper::toDomain).toList();

        if (productIds.size() != products.size()) {
            throw new FastFoodException(
                    "Produto não encontrado",
                    "Um ou mais produtos não existem",
                    HttpStatus.NOT_FOUND
            );
        }

        final Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        order.getItens().forEach(orderItem -> {
            final Product product = productMap.get(orderItem.getProduct().getId());
            if (product != null) {
                orderItem.setProduct(product);
            }
        });
        order.updateTotalPrice();
        final OrderEntity orderEntity = OrderMapper.toEntity(order);

        orderEntity.getItens().forEach(orderItemEntity -> {
            orderItemEntity.setOrder(orderEntity);
        });

        final OrderEntity save = orderEntityRepository.save(orderEntity);
        List<OrderItemEntity> all = iOrderItemRepository.findAll();
        return OrderMapper.toDomain(save);
    }
}
