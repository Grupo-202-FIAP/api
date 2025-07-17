package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.product.UpdateProductUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(Product product, Long idProduct) {
        try {
            product.setId(idProduct);
            return productRepository.update(product);
        } catch (DataIntegrityViolationException ex) {
            throw new FastFoodException("Product name already in use", "Product name already in use", HttpStatus.CONFLICT);
        }
    }
}
