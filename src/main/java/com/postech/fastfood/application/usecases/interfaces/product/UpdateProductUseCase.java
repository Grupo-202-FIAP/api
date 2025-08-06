package com.postech.fastfood.application.usecases.interfaces.product;

import com.postech.fastfood.domain.Product;

public interface UpdateProductUseCase {
    Product execute(Product product, Long idProduct);
}
