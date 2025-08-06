package com.postech.fastfood.core.usecase.product;

import com.postech.fastfood.core.domain.Product;

public interface UpdateProductUseCase {
    Product execute(Product product, Long idProduct);
}
