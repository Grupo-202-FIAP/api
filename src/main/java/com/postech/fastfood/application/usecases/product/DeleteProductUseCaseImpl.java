package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.infrastructure.gateways.product.DeleteProductUseCase;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Long idProduct) {
        this.productRepository.delete(idProduct);
    }
}
