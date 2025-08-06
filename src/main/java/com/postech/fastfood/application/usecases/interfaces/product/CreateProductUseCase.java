package com.postech.fastfood.application.usecases.interfaces.product;

import com.postech.fastfood.domain.Product;

public interface CreateProductUseCase {
    Product execute(Product product);
}
