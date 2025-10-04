package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCreateCategory {
    private Long id;
    private String name;
    private Integer cantProducts;
}
