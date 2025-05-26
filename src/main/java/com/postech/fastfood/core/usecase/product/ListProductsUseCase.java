package com.postech.fastfood.core.usecase.product;

import java.util.List;
import com.postech.fastfood.core.domain.Product;

public interface ListProductsUseCase {
    List<Product> execute();
}
