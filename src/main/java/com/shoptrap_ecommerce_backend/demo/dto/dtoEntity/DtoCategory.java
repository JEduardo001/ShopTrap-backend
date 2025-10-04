package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCategory {
    private Long id;
    private String name;
    private Integer cantProducts;
}
