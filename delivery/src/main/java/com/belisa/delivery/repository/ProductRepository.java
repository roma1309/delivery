package com.belisa.delivery.repository;

import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByStatus(ProductStatus status);

//    List<ProductEntity> findByCreationDate(LocalDate date);
}
