package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DtoCreateProduct {
    @NotBlank(message = "El nombre de producto no puede estar vacio")
    private String name;
    @NotNull(message = "El precio no puede estar vacio")
    private BigDecimal price;
    private Integer dicount;
}
