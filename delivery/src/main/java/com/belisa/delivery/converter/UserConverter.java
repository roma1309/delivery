package com.belisa.delivery.converter;

import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.dto.UserDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.UserEntity;

public class UserConverter {

    public static UserDto EntityToDto(UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getUsername());
    }
}
