package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.ProductDTO;
import com.renuox.supermarket.model.SaleDetail;
import com.renuox.supermarket.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {

    private final SaleRepository saleRepository;

    public ProductDTO getBestSellingProduct() {

        return saleRepository.findAll().stream()
                // Convert Stream<Sale> to Stream<SaleDetail>
                .flatMap(sale -> sale.getDetail().stream())
                // Group by product and add quantities
                .collect(
                        Collectors.groupingBy(
                                SaleDetail::getProduct,
                                Collectors.summingInt(SaleDetail::getQuantity)
                        )
                )
                // Obtain product with more quantity
                .entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(entry ->
                        new ProductDTO(
                                entry.getKey().getId(),
                                entry.getKey().getName(),
                                entry.getKey().getCategory(),
                                entry.getValue(),
                                entry.getKey().getAmount()
                        )
                )
                .orElse(null);
    }
}
