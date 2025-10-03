package com.shoptrap_ecommerce_backend.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "shopping_car_product")
public class ShoppingCarProductEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @OneToOne
   @JoinColumn(name = "product_id")
   private ProductEntity product;
   @ManyToOne
   @JoinColumn(name = "shopping_car_id")
   private ShoppingCarEntity shoppingCar;
   @OneToOne
   @JoinColumn(name = "user_id")
   private UserEntity user;
   private Integer amount;

}
