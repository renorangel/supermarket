package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.BranchDTO;
import com.renuox.supermarket.exception.NotFoundException;
import com.renuox.supermarket.mapper.Mapper;
import com.renuox.supermarket.model.Branch;
import com.renuox.supermarket.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Autowired
    private BranchRepository repo;

    @Override
    public List<BranchDTO> list() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO create(BranchDTO branchDto) {
        Branch branch = Branch.builder().name(branchDto.getName()).address(branchDto.getAddress()).build();

        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public BranchDTO update(Long id, BranchDTO branchDto) {
        Branch branch = repo.findById(id).orElseThrow(() -> new NotFoundException("Branch don't found"));

        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());

        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Branch don't found");
        repo.deleteById(id);
    }
}
