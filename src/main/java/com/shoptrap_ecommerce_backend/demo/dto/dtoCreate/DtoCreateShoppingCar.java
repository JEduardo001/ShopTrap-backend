package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCreateShoppingCar {
    @NotNull(message = "Debes proporcionar un usuario para crear un carrito de compras")
    private UserEntity user;

}
