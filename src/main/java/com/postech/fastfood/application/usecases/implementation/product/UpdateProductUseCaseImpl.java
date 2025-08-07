package com.postech.fastfood.application.usecases.implementation.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.application.usecases.interfaces.product.UpdateProductUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product execute(Product product, Long idProduct) {
        try {
            product.setId(idProduct);
            return productRepositoryPort.update(product);
        } catch (DataIntegrityViolationException ex) {
            throw new FastFoodException("Product name already in use", "Product name already in use", HttpStatus.CONFLICT);
        }
    }
}
