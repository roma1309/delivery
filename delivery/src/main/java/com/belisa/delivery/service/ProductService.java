package com.belisa.delivery.service;

import com.belisa.delivery.dto.NewProductDto;
import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    public ProductDto createProduct(NewProductDto newProductDto);
    public void updateProductStatusToInTransit();

    public ProductDto markProductAsDelivered(Long productId);
    public List<ProductDto> getAll();

}
