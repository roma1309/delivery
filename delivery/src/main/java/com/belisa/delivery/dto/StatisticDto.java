package com.belisa.delivery.dto;

import com.belisa.delivery.entity.enums.ProductCategory;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StatisticDto {
    private Map<ProductCategory, Long> categoryCount = new HashMap<>();
    private LocalDateTime createdAt;

    public Map<ProductCategory, Long> getCategoryCount() {
        return categoryCount;
    }


    public void setCategoryCount(Map<ProductCategory, Long> categoryCount) {
        this.categoryCount = categoryCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
