package com.belisa.delivery.repository;

import com.belisa.delivery.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<StatisticEntity, Long> {
    public Optional<List<StatisticEntity>> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

}
