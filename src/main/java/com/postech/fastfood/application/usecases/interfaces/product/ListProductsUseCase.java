package com.postech.fastfood.application.usecases.interfaces.product;

import com.postech.fastfood.domain.Product;
import java.util.List;

public interface ListProductsUseCase {
    List<Product> execute();
}
