package com.shoptrap_ecommerce_backend.demo.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class DtoFilter {
    private Long idCategory;
    private BigInteger price;
    private Boolean discount;
}
