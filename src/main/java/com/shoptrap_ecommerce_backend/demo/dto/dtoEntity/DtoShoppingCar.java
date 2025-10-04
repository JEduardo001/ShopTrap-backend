package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoShoppingCar {

    private Long id;
    private UserEntity user;
}
