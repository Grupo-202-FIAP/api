package com.postech.fastfood.core.service.order;

import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public CreateOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    private static final int END_ID = 4;

    @Override
    public Order execute(Order order) {
        if (order.getItens() == null || order.getItens().isEmpty()) {
            throw new FastFoodException(
                    "Order item list cannot be null or empty",
                    "Order item NULL or EMPTY",
                    HttpStatus.BAD_REQUEST);
        }
        order.setIdentifier(generateOrderId());
        return orderRepositoryPort.save(order);
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
