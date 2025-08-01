package com.postech.fastfood.core.usecase.product;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import java.util.List;

public interface ListProductByCategoryUseCase {
    List<Product> execute(Category category);
}

