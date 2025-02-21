package com.belisa.delivery.service.impl;

import com.belisa.delivery.converter.ProductConverter;
import com.belisa.delivery.dto.NewProductDto;
import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.enums.ProductCategory;
import com.belisa.delivery.entity.enums.ProductStatus;
import com.belisa.delivery.repository.ProductRepository;
import com.belisa.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(NewProductDto newProductDto) {
        if (newProductDto.getSenderId() == newProductDto.getReceiverId()) {
            throw new RuntimeException("Курьер и получатель не могут быть одним пользователем");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(ProductCategory.getProductCategory(newProductDto.getCategory()));
        productEntity.setName(newProductDto.getName());
        productEntity.setCourier(newProductDto.getCourier());
        productEntity.setStatus(ProductStatus.ON_WAREHOUSE);
        return ProductConverter.EntityToDto(productRepository.save(productEntity));
    }

    @Scheduled(cron = "0 0 4 * * ?")
    @Override
    public void updateProductStatusToInTransit() {
        LocalDateTime dayNow = LocalDateTime.now();
        List<ProductEntity> products = productRepository.findByStatus(ProductStatus.ON_WAREHOUSE).stream().filter(productEntity ->
                productEntity.getCreatedAt().isBefore(dayNow)).collect(Collectors.toList());
        for (ProductEntity product : products) {
            product.setStatus(ProductStatus.IN_TRANSIT);
            productRepository.save(product);
        }
    }

    @Override
    public ProductDto markProductAsDelivered(Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow();
        if(!product.getStatus().equals(ProductStatus.IN_TRANSIT)){
            throw new RuntimeException("Товар должен иметь статус в пути");
        }
        product.setStatus(ProductStatus.DELIVERED);
        return ProductConverter.EntityToDto(productRepository.save(product));
    }
}

