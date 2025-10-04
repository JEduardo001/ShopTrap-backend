package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryCategory extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByName(String name);
}
