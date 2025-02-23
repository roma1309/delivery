package com.belisa.delivery.service.impl;

import com.belisa.delivery.converter.StatisticConverter;
import com.belisa.delivery.dto.StatisticDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.StatisticEntity;
import com.belisa.delivery.entity.enums.ProductCategory;
import com.belisa.delivery.repository.ProductRepository;
import com.belisa.delivery.repository.StatisticRepository;
import com.belisa.delivery.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository, ProductRepository productRepository) {
        this.statisticRepository = statisticRepository;
        this.productRepository = productRepository;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public StatisticEntity generateStatistics() {

        List<ProductEntity> products = productRepository.findAll();

        Map<ProductCategory, Long> categoryStats = products.stream()
                .collect(Collectors.groupingBy(
                        ProductEntity::getCategory,
                        Collectors.counting()
                ));
        StatisticEntity statistic = new StatisticEntity(categoryStats);
        return statisticRepository.save(statistic);
    }

    @Override
    public List<StatisticDto> getStatistics(LocalDate startDate, LocalDate endDate) {
        List<StatisticEntity> statisticEntityList = statisticRepository.findByCreatedAtBetween(startDate, endDate).orElseThrow(() -> new RuntimeException("Statistics not found"));
        return statisticEntityList.stream().map(StatisticConverter::EntityToDto).collect(Collectors.toList());
    }
}
