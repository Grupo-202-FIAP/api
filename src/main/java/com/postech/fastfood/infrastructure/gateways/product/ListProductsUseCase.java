package com.postech.fastfood.infrastructure.gateways.product;

import com.postech.fastfood.domain.Product;
import java.util.List;

public interface ListProductsUseCase {
    List<Product> execute();
}
