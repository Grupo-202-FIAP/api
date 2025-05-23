package com.postech.fastfood.core.usecase.product;

import com.postech.fastfood.core.domain.Product;

public interface CreateProductUseCase {
    Product execute(Product product);
}
