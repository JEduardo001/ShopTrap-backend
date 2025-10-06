package com.shoptrap_ecommerce_backend.demo.projection;

import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    List<CategoryProjection> getCategory();
    Boolean getHasDiscount();

    interface CategoryProjection {
        Long getId();
        String getName();
    }
}


