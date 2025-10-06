package com.shoptrap_ecommerce_backend.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime dateCreated;
    private LocalDateTime deletionDate;
    private Integer dicount;
    private Boolean hasDiscount;
    private Integer stock;
    @ManyToMany()
    @JoinTable(
            name = "products_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    private List<CategoryEntity> category;


}
