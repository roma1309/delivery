package com.belisa.delivery.service;

import com.belisa.delivery.dto.StatisticDto;
import com.belisa.delivery.entity.StatisticEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    public List<StatisticDto> getStatistics(LocalDate startDate, LocalDate endDate);
}
