package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryShoppingCar extends JpaRepository<ShoppingCarEntity,Long> {
}

