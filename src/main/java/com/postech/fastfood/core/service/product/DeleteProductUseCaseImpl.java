package com.postech.fastfood.core.service.product;

import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void execute(Long id_product) {
        this.productRepositoryPort.delete(id_product);
    }
}
