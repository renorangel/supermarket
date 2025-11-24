package com.renuox.supermarket.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {

    //sale data
    private Long id;
    private LocalDate date;
    private String state;

    //Branch data
    private Long idBranch;

    //detail list
    private List<SaleDetailDTO> detail;

    //sale total
    private Double totalAmount;

}
