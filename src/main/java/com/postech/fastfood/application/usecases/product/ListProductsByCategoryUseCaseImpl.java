package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.enums.Category;
import com.postech.fastfood.infrastructure.gateways.product.ListProductByCategoryUseCase;
import java.util.List;

public class ListProductsByCategoryUseCaseImpl implements ListProductByCategoryUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public ListProductsByCategoryUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> execute(Category category) {
        return productRepositoryPort.findProductByCategory(category);
    }
}
