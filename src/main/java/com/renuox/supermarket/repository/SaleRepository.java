package com.renuox.supermarket.repository;

import com.renuox.supermarket.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
