package com.belisa.delivery.converter;

import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.entity.ProductEntity;

public class ProductConverter {

//    public static ProductEntity dtoToEntity() {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setTask(taskEntity);
//        commentEntity.setText(commentDTO.getMessage());
//        commentEntity.setUserId(userEntity.getId());
//        return commentEntity;
//    }

    public static ProductDto EntityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setName(productEntity.getName());
        productDto.setCategory(productEntity.getCategory().name());
        productDto.setCourier(productEntity.getCourier());
        productDto.setStatus(productEntity.getStatus().name());
        productDto.setSender(productEntity.getSender().getUsername());
        productDto.setReceiver(productEntity.getReceiver().getUsername());
        productDto.setId(productEntity.getId());
        return productDto;
    }
}
