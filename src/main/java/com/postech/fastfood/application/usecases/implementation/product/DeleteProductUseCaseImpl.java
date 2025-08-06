package com.postech.fastfood.application.usecases.implementation.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.application.usecases.interfaces.product.DeleteProductUseCase;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void execute(Long idProduct) {
        this.productRepositoryPort.delete(idProduct);
    }
}
