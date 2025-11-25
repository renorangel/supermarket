package com.renuox.supermarket.controller;

import com.renuox.supermarket.dto.ProductDTO;
import com.renuox.supermarket.exception.NotFoundException;
import com.renuox.supermarket.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticService statisticService;

    @GetMapping("/best-selling-product")
    public ResponseEntity<?> getBestSellingProduct() {
        ProductDTO dto = statisticService.getBestSellingProduct();
        if (dto == null) {
            throw new NotFoundException("Product don't found to delete");
        }
        return ResponseEntity.ok(dto);
    }
}
