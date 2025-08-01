package com.postech.fastfood.infrastructure.gateways.product;

import com.postech.fastfood.domain.Product;

public interface UpdateProductUseCase {
    Product execute(Product product, Long idProduct);
}
