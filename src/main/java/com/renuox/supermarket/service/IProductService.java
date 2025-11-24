package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> list();

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(Long id, ProductDTO productDTO);

    void delete(Long id);
}
