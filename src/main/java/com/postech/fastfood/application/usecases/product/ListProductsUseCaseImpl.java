package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.infrastructure.gateways.product.ListProductsUseCase;
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
