package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.ProductEntity;
import com.shoptrap_ecommerce_backend.demo.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryProduct extends JpaRepository<ProductEntity,Long> {
    Page<ProductProjection> findAllByCategoryId(Pageable pageable, Long idCategory);
    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.category")
    Page<ProductProjection> findAllByProjection(Pageable pageable);

}

