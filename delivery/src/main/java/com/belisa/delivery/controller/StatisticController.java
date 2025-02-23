package com.belisa.delivery.controller;


import com.belisa.delivery.dto.NewProductDto;
import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.dto.StatisticDto;
import com.belisa.delivery.entity.ProductEntity;
import com.belisa.delivery.entity.StatisticEntity;
import com.belisa.delivery.service.ProductService;
import com.belisa.delivery.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stat")
@CrossOrigin
public class StatisticController {
    private final StatisticService statisticService;
    private final ProductService productService;

    @Autowired
    public StatisticController(StatisticService statisticService, ProductService productService) {
        this.statisticService = statisticService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<StatisticDto>> getStatistics(@RequestParam("dateFrom") String dateFrom,
                                                            @RequestParam("dateTo") String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        return  new ResponseEntity<>(statisticService.getStatistics(startDate, endDate), HttpStatus.OK);
    }
}
