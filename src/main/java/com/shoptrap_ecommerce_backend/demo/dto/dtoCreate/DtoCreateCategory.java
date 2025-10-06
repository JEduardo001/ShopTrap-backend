package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCreateCategory {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotNull(message = "La cantidad de productos no puede estar vacio")
    private Integer cantProducts;
}
