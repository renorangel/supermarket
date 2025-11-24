package com.renuox.supermarket.controller;

import com.renuox.supermarket.dto.SaleDTO;
import com.renuox.supermarket.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> list() {

        return ResponseEntity.ok(saleService.list());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@RequestBody SaleDTO dto) {
        SaleDTO created = saleService.create(dto);

        return ResponseEntity.created(URI.create("/api/sales" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@PathVariable Long id,
                                          @RequestBody SaleDTO dto) {
        return ResponseEntity.ok(saleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
