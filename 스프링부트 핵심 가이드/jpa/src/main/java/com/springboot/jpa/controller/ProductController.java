package com.springboot.jpa.controller;

import com.springboot.jpa.data.entity.ChangeProductNameDto;
import com.springboot.jpa.data.entity.ProductDto;
import com.springboot.jpa.data.entity.ProductResponseDto;
import com.springboot.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(@RequestParam Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto
    ) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
            changeProductNameDto.getNumber(),
            changeProductNameDto.getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제됐습니다.");
    }
}
