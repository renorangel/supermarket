package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.SaleDTO;

import java.util.List;

public interface ISaleService {
    List<SaleDTO> list();

    SaleDTO create(SaleDTO saleDTO);

    SaleDTO update(Long id, SaleDTO saleDTO);

    void delete(Long id);
}
