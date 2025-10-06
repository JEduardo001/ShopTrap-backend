package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DtoCreateProduct {
    @NotBlank(message = "El nombre de producto no puede estar vacio")
    private String name;
    @NotNull(message = "El precio no puede estar vacio")
    private BigDecimal price;
    @NotNull(message = "El descuento no puede estar vacio")
    private Integer discount;
    @NotNull(message = "Especificacion de si tiene descuento no puede estar vacio")
    private Boolean hasDiscount;
    @NotNull(message = "Debes asociar al producto almenos una categoria")
    private List<CategoryEntity> category;
}
