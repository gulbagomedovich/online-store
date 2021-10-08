package com.gulbagomedovich.productservice.service.impl;

import com.gulbagomedovich.productservice.dto.ProductDto;
import com.gulbagomedovich.productservice.model.Product;
import com.gulbagomedovich.productservice.repository.ProductRepository;
import com.gulbagomedovich.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        log.info("Product was created: {}", product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.getById(id);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProductById(Long id, ProductDto newProductDto) {
        Product product = productRepository.getById(id);
        product.setTitle(newProductDto.getTitle());
        product.setDescription(newProductDto.getDescription());
        product.setAmount(newProductDto.getAmount());
        productRepository.save(product);
        log.info("Product with id={} was updated: {}", product.getId(), product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
        log.info("Product with id={} was deleted", id);
    }
}
