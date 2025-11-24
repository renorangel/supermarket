package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.BranchDTO;

import java.util.List;

public interface IBranchService {
    List<BranchDTO> list();

    BranchDTO create(BranchDTO branchDto);

    BranchDTO update(Long id, BranchDTO branchDto);

    void delete(Long id);
}
