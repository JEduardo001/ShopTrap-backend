package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DtoShoppingCar {
    private Long id;
    private DtoUser user;
    private List<DtoShoppingCarProduct> products;
}
