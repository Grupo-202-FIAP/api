package com.postech.fastfood.core.service.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import com.postech.fastfood.core.domain.Order;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public CreateOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order execute(Order order) {
        order.setIdentifier(generateOrderId());
        return orderRepositoryPort.save(order);
    }

    public String generateOrderId() {
        String shortUUID = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String datetime = LocalDateTime.now().format(dtf);
        String shuffledDateTime = shuffleString(datetime);
        return "ORD-" + shortUUID + "-" + shuffledDateTime.substring(0, 4);
    }

    private String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder output = new StringBuilder(input.length());
        for (char c : characters) {
            output.append(c);
        }
        return output.toString();
    }


}
