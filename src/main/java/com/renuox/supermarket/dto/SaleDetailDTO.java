package com.renuox.supermarket.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private Double amount;
    private Double subtotal;
}
