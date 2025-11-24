package com.renuox.supermarket.mapper;

import com.renuox.supermarket.dto.BranchDTO;
import com.renuox.supermarket.dto.ProductDTO;
import com.renuox.supermarket.dto.SaleDTO;
import com.renuox.supermarket.dto.SaleDetailDTO;
import com.renuox.supermarket.model.Branch;
import com.renuox.supermarket.model.Product;
import com.renuox.supermarket.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    //Mapping de Product a ProductDTO
    public static ProductDTO toDTO(Product p) {
        if (p == null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .amount(p.getAmount())
                .quantity(p.getQuantity())
                .build();
    }

    //Mapping de Sale a SaleDTO
    public static SaleDTO toDTO(Sale sale) {
        if (sale == null) return null;

        var detail = sale.getDetail().stream().map(det ->
                SaleDetailDTO.builder()
                        .id(det.getProduct().getId())
                        .name(det.getProduct().getName())
                        .quantity(det.getQuantity())
                        .amount(det.getAmount())
                        .subtotal(det.getAmount() * det.getQuantity())
                        .build()
        ).collect(Collectors.toList());

        var totalAmount = detail.stream()
                .map(SaleDetailDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .idBranch(sale.getBranch().getId())
                .state(sale.getState())
                .detail(detail)
                .totalAmount(totalAmount)
                .build();
    }

    //Mapping de Branch a BranchDTO
    public static BranchDTO toDTO(Branch b) {
        if (b == null) return null;
        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();
    }

}