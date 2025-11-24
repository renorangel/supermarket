package com.renuox.supermarket.repository;

import com.renuox.supermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Find product por name
    Optional<Product> findByName(String name);
}
