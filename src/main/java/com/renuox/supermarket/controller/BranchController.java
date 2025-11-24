package com.renuox.supermarket.controller;

import com.renuox.supermarket.dto.BranchDTO;
import com.renuox.supermarket.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> list() {

        return ResponseEntity.ok(branchService.list());
    }

    @PostMapping
    public ResponseEntity<BranchDTO> create(@RequestBody BranchDTO dto) {
        BranchDTO created = branchService.create(dto);

        return ResponseEntity.created(URI.create("/api/branches" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> update(@PathVariable Long id,
                                            @RequestBody BranchDTO dto) {
        return ResponseEntity.ok(branchService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
