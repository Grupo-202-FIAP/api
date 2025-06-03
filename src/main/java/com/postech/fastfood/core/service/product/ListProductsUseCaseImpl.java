package com.postech.fastfood.core.service.product;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import java.util.List;

public class ListProductsUseCaseImpl implements ListProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ListProductsUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> execute() {
        return productRepositoryPort.findAll();
    }
}
