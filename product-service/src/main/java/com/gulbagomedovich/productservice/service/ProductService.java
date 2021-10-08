package com.gulbagomedovich.productservice.service;

import com.gulbagomedovich.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto updateProductById(Long id, ProductDto newProductDto);

    void deleteProductById(Long id);
}
