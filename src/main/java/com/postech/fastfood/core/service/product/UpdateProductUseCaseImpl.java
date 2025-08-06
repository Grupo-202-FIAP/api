package com.postech.fastfood.core.service.product;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
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
            throw new FastFoodException(
                    "Product name already in use",
                    "Product name already in use",
                    HttpStatus.CONFLICT
            );
        }
    }
}
