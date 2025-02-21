package com.belisa.delivery.converter;

import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.dto.StatisticDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.StatisticEntity;

public class StatisticConverter {
    public static StatisticDto EntityToDto(StatisticEntity statisticEntity) {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setCreatedAt(statisticEntity.getCreatedAt());
        statisticDto.setCategoryCount(statisticEntity.getCategoryCount());
        return statisticDto;
    }
}
