package com.shoptrap_ecommerce_backend.demo.projection;

import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    LocalDateTime getDateCreated();
    LocalDateTime getDeletionDate();
    Integer getDicount();
    Integer getStock();
    List<DtoCategory> getCategory();
}

