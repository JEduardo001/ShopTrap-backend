package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import com.shoptrap_ecommerce_backend.demo.entity.ProductEntity;
import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarEntity;
import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoShoppingCarProduct {

    private Long id;
    private DtoProduct product;
    private Integer amount;
}
