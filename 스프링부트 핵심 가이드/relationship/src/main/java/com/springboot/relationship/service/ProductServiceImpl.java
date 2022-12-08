package com.springboot.relationship.service;

import com.springboot.relationship.data.dao.ProductDAO;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.dto.ProductDto;
import com.springboot.relationship.data.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .number(product.getNumber())
                .price(product.getPrice())
                .stock(product.getStock())
                .name(product.getName())
                .build();

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .number(savedProduct.getNumber())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .build();

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changeProduct = productDAO.updateProductName(number, name);
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .number(changeProduct.getNumber())
                .name(changeProduct.getName())
                .price(changeProduct.getPrice())
                .stock(changeProduct.getStock())
                .build();

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
