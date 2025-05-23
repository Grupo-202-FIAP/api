package com.postech.fastfood.core.service.product;

import java.util.List;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;

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
