package com.belisa.delivery.service.impl;

import com.belisa.delivery.converter.ProductConverter;
import com.belisa.delivery.dto.NewProductDto;
import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.UserEntity;
import com.belisa.delivery.entity.enums.ProductCategory;
import com.belisa.delivery.entity.enums.ProductStatus;
import com.belisa.delivery.exceptions.ReceiverSenderOneUser;
import com.belisa.delivery.exceptions.StatusException;
import com.belisa.delivery.repository.ProductRepository;
import com.belisa.delivery.repository.UserRepository;
import com.belisa.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private  final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductDto createProduct(NewProductDto newProductDto) {
        if (newProductDto.getSenderId() == newProductDto.getReceiverId()) {
            throw new ReceiverSenderOneUser("Курьер и получатель не могут быть одним пользователем");
        }
        UserEntity receiver = userRepository.findById(newProductDto.getReceiverId()).orElseThrow(() -> new RuntimeException("Not found user"));
        UserEntity sender = userRepository.findById(newProductDto.getSenderId()).orElseThrow(() -> new RuntimeException("Not found user"));
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(ProductCategory.getProductCategory(newProductDto.getCategory()));
        productEntity.setName(newProductDto.getName());
        productEntity.setCourier(newProductDto.getCourier());
        productEntity.setStatus(ProductStatus.ON_WAREHOUSE);
        productEntity.setReceiver(receiver);
        productEntity.setSender(sender);
        return ProductConverter.EntityToDto(productRepository.save(productEntity));
    }

    @Scheduled(cron = "0 0 4 * * ?")
    @Override
    public void updateProductStatusToInTransit() {
        LocalDate dayNow = LocalDate.now();
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
            throw new StatusException("Товар должен иметь статус в пути");
        }
        product.setStatus(ProductStatus.DELIVERED);
        return ProductConverter.EntityToDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductConverter::EntityToDto).collect(Collectors.toList());
    }
}

