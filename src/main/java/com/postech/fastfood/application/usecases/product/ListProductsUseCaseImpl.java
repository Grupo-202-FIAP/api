package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.infrastructure.gateways.product.ListProductsUseCase;
import java.util.List;

public class ListProductsUseCaseImpl implements ListProductsUseCase {

    private final ProductRepository productRepository;

    public ListProductsUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> execute() {
        return productRepository.findAll();
    }
}
