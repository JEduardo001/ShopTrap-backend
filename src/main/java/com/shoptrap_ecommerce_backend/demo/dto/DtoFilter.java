package com.shoptrap_ecommerce_backend.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DtoFilter {
    private Long idCategory;
    private BigDecimal price;
    private Boolean discount;
}
