package com.postech.fastfood.application.usecases.order;

import com.postech.fastfood.application.gateways.CustomerRepository;
import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.domain.Customer;
import com.postech.fastfood.domain.Order;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.enums.OrderStatus;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.order.CreateOrderUseCase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private static final int END_ID = 4;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CreateOrderUseCaseImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order execute(Order order) {

        if (order.getItens() == null || order.getItens().isEmpty()) {
            throw new FastFoodException("Order item list cannot be null or empty", "Order item NULL or EMPTY", HttpStatus.BAD_REQUEST);
        }

        order.setIdentifier(generateOrderId());
        order.setStatus(OrderStatus.RECEIVED);

        if (order.getCustomer().getId() != null) {
            final Customer customer = customerRepository.findById(order.getCustomer().getId());
            if (customer != null) {
                order.setCustomer(customer);
            } else {
                throw new FastFoodException("Cliente não encontrado", "Cliente com ID " + order.getCustomer().getId() + " não encontrado",
                        HttpStatus.NOT_FOUND);
            }
        } else {
            order.setCustomer(null);
        }

        final List<Long> productIds = order.getItens().stream().map(orderItem -> orderItem.getProduct().getId()).toList();

        final List<Product> products = productRepository.findAllById(productIds);

        if (productIds.size() != products.size()) {
            throw new FastFoodException("Produto não encontrado", "Um ou mais produtos não existem", HttpStatus.NOT_FOUND);
        }

        final Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        order.getItens().forEach(orderItem -> {
            final Product product = productMap.get(orderItem.getProduct().getId());
            if (product != null) {
                orderItem.setProduct(product);
                orderItem.setPriceAtPurchase(orderItem.getProduct().getUnitPrice());
            }
        });

        order.updateTotalPrice();

        order.getItens().forEach(orderItemEntity -> {
            orderItemEntity.setOrder(order);
        });

        return orderRepository.save(order);
    }

    public String generateOrderId() {
        final String shortUUID = UUID.randomUUID().toString().substring(0, END_ID).toUpperCase();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        final String datetime = LocalDateTime.now().format(dtf);
        final String shuffledDateTime = shuffleString(datetime);
        return "ORD-" + shortUUID + "-" + shuffledDateTime.substring(0, END_ID);
    }

    private String shuffleString(String input) {
        final List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        final StringBuilder output = new StringBuilder(input.length());
        for (char c : characters) {
            output.append(c);
        }
        return output.toString();
    }
}
