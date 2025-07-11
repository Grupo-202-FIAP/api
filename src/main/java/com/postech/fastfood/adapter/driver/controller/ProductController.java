package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.ProductRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductUpdateRequest;
import com.postech.fastfood.adapter.driver.controller.dto.response.ProductsResponse;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.usecase.product.CreateProductUseCase;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;
import com.postech.fastfood.core.usecase.product.ListProductByCategoryUseCase;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ListProductByCategoryUseCase listProductByCategoryUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             ListProductsUseCase listProductsUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             ListProductByCategoryUseCase listProductByCategoryUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.listProductByCategoryUseCase = listProductByCategoryUseCase;
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductsResponse>> listProductByCategory(@RequestParam Category category) {
        final List<Product> products = listProductByCategoryUseCase.execute(category);
        final List<ProductsResponse> response = products.stream()
                .map(ProductMapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product productCreated = createProductUseCase.execute(domain);
        final ProductsResponse productCreatedResponse = ProductMapper.toResponse(productCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreatedResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductsResponse>> listProduct() {
        final List<Product> products = listProductsUseCase.execute();
        final List<ProductsResponse> response = products.stream()
                .map(ProductMapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<ProductsResponse> updateProduct(@RequestBody @Valid ProductUpdateRequest productRequest, @RequestParam Long id) {
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product execute = this.updateProductUseCase.execute(domain, id);
        final ProductsResponse response = ProductMapper.toResponse(execute);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
