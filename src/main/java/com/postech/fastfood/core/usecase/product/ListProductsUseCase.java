package com.postech.fastfood.core.usecase.product;

import com.postech.fastfood.core.domain.Product;
import java.util.List;

public interface ListProductsUseCase {
    List<Product> execute();
}
