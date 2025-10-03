package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategory extends JpaRepository<CategoryEntity,Long> {
}
