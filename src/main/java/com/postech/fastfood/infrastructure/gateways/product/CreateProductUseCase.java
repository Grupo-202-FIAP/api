package com.postech.fastfood.infrastructure.gateways.product;

import com.postech.fastfood.domain.Product;

public interface CreateProductUseCase {
    Product execute(Product product);
}
