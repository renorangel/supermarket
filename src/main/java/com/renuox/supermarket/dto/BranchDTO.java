package com.renuox.supermarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {

    private Long id;
    private String name;
    private String address;
}
