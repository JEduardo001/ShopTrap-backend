package com.shoptrap_ecommerce_backend.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer cantProducts;
    @ManyToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

}
