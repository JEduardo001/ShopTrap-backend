package com.shoptrap_ecommerce_backend.demo.repository;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<UserEntity,Long> {
    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

}
