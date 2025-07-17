package com.postech.fastfood.application.usecases.product;

import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.enums.Category;
import com.postech.fastfood.infrastructure.gateways.product.ListProductByCategoryUseCase;
import java.util.List;

public class ListProductsByCategoryUseCaseImpl implements ListProductByCategoryUseCase {
    private final ProductRepository productRepository;

    public ListProductsByCategoryUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> execute(Category category) {
        return productRepository.findProductByCategory(category);
    }
}
