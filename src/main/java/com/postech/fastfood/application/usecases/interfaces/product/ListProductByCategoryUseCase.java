package com.postech.fastfood.application.usecases.interfaces.product;

import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.enums.Category;
import java.util.List;

public interface ListProductByCategoryUseCase {
    List<Product> execute(Category category);
}

