package com.belisa.delivery.entity;

import com.belisa.delivery.entity.enums.ProductCategory;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "statistics")
public class StatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "category_statistics")
    @Column(name = "count")
    private Map<ProductCategory, Long> categoryCount = new HashMap<>();

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }
    public StatisticEntity() {
    }

    public StatisticEntity( Map<ProductCategory, Long> categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<ProductCategory, Long> getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Map<ProductCategory, Long> categoryCount) {
        this.categoryCount = categoryCount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
