package com.renuox.supermarket.service;

import com.renuox.supermarket.dto.SaleDTO;
import com.renuox.supermarket.dto.SaleDetailDTO;
import com.renuox.supermarket.exception.NotFoundException;
import com.renuox.supermarket.mapper.Mapper;
import com.renuox.supermarket.model.Branch;
import com.renuox.supermarket.model.Product;
import com.renuox.supermarket.model.Sale;
import com.renuox.supermarket.model.SaleDetail;
import com.renuox.supermarket.repository.BranchRepository;
import com.renuox.supermarket.repository.ProductRepository;
import com.renuox.supermarket.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private SaleRepository saleRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BranchRepository branchRepo;

    @Override
    public List<SaleDTO> list() {

        List<Sale> sales = saleRepo.findAll();
        List<SaleDTO> salesDto = new ArrayList<>();

        for (Sale s : sales) {
            salesDto.add(Mapper.toDTO(s));
        }

        return salesDto;
    }

    @Override
    public SaleDTO create(SaleDTO saleDto) {

        //Validations
        if (saleDto == null) throw new RuntimeException("SaleDTO is null");
        if (saleDto.getIdBranch() == null) throw new RuntimeException("It must Start branch");
        if (saleDto.getDetail() == null || saleDto.getDetail().isEmpty())
            throw new RuntimeException("It must include at least one product");

        //Find the branch
        Branch branch = branchRepo.findById(saleDto.getIdBranch()).orElse(null);
        if (branch == null) {
            throw new NotFoundException("Branch don't found");
        }

        //Create the sale
        Sale sale = new Sale();
        sale.setDate(saleDto.getDate());
        sale.setState(saleDto.getState());
        sale.setBranch(branch);
//        sale.setTotal(saleDto.getTotalAmount());

        // detail list
        // --> Here are the products
        List<SaleDetail> details = new ArrayList<>();
        double totalCalculated = 0.0;

        for (SaleDetailDTO detDTO : saleDto.getDetail()) {
            // Find product by id (detDTO use id like id product)
            Product p = productRepo.findByName(detDTO.getName()).orElse(null);
            if (p == null) {
                throw new RuntimeException("Product don't found: " + detDTO.getName());
            }

            /* Create detail */
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(p);
            saleDetail.setAmount(detDTO.getAmount());
            saleDetail.setQuantity(detDTO.getQuantity());
            saleDetail.setSale(sale);

            details.add(saleDetail);
            totalCalculated += (detDTO.getAmount() * detDTO.getQuantity());

        }
        sale.setTotal(totalCalculated);
        /* Streaming sale detail list */
        sale.setDetail(details);

        /* Save in DB */
        sale = saleRepo.save(sale);

        /* Output mapping */
        return Mapper.toDTO(sale);
    }

    @Override
    public SaleDTO update(Long id, SaleDTO saleDto) {
        /* Check if the sale exists to update it */
        Sale sale = saleRepo.findById(id).orElse(null);
        if (sale == null) throw new RuntimeException("Sale not found");

        if (saleDto.getDate() != null) {
            sale.setTotal(saleDto.getTotalAmount());
        }
        if (saleDto.getState() != null) {
            sale.setState(saleDto.getState());
        }
        if (saleDto.getTotalAmount() != null) {
            sale.setTotal(saleDto.getTotalAmount());
        }

        if (saleDto.getIdBranch() != null) {
            Branch suc = branchRepo.findById(saleDto.getIdBranch()).orElse(null);
            if (suc == null) throw new NotFoundException("Branch don't found");
            sale.setBranch(suc);
        }
        saleRepo.save(sale);
        return Mapper.toDTO(sale);
    }

    @Override
    public void delete(Long id) {
        Sale sale = saleRepo.findById(id).orElse(null);
        if (sale == null) throw new RuntimeException("Sale don't found");
        saleRepo.delete(sale);

    }
}
