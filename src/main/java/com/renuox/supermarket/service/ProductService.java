package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.ProductDTO;
import com.renuox.supermarket.exception.NotFoundException;
import com.renuox.supermarket.mapper.Mapper;
import com.renuox.supermarket.model.Product;
import com.renuox.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> list() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .category(productDTO.getCategory())
                .amount(productDTO.getAmount())
                .quantity(productDTO.getQuantity())
                .build();
        return Mapper.toDTO(repo.save(product));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {

        //find product
        Product product = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product don't found"));

        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        product.setAmount(productDTO.getAmount());

        return Mapper.toDTO(repo.save(product));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Product don't found to delete");
        }
        repo.deleteById(id);
    }
}
