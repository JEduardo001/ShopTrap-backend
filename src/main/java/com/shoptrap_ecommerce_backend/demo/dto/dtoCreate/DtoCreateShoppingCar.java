package com.shoptrap_ecommerce_backend.demo.dto.dtoCreate;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCreateShoppingCar {
    private UserEntity user;

}
