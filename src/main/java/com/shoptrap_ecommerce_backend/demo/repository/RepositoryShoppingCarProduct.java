package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryShoppingCarProduct extends JpaRepository<ShoppingCarProductEntity,Long> {
}

