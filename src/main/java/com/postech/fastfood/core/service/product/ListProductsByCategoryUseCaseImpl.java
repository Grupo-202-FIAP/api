package com.postech.fastfood.core.service.product;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.usecase.product.ListProductByCategoryUseCase;
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
