package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.product.CreateProductUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public CreateProductUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product execute(Product product) {
        try {
            return productRepositoryPort.save(product);
        } catch (DataIntegrityViolationException ex) {
            throw new FastFoodException("Product name already in use", "Product name already in use", HttpStatus.CONFLICT);
        }
    }
}
