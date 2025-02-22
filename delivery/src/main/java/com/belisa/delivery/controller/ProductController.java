package com.belisa.delivery.controller;

import com.belisa.delivery.dto.NewProductDto;
import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody NewProductDto newProductDto) {
        return new ResponseEntity<>(productService.createProduct(newProductDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{product_id}")
    public ResponseEntity<ProductDto> updateStatusAsDelivered(@PathVariable("product_id") final String productId) {
        return new ResponseEntity<>(productService.markProductAsDelivered(Long.parseLong(productId)), HttpStatus.CREATED);
    }
}
