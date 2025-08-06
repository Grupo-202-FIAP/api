package com.postech.fastfood.application.usecases.implementation.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.application.usecases.interfaces.product.ListProductsUseCase;
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
