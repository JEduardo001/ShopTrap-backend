package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProduct {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime dateCreated;
    private LocalDateTime deletionDate;
    private Integer discount;
    private Boolean hasDiscount;
    private Integer stock;
}
