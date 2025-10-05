package com.shoptrap_ecommerce_backend.demo.service;

import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoShoppingCarProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.entity.ProductEntity;
import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarEntity;
import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarProductEntity;
import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.*;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryProduct;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryShoppingCar;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryShoppingCarProduct;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCarService {

    private RepositoryShoppingCar repositoryShoppingCar;
    private UserService userService;
    private RepositoryProduct repositoryProduct;
    private RepositoryShoppingCarProduct repositoryShoppingCarProduct;


    public ShoppingCarService(RepositoryShoppingCar repositoryShoppingCar,UserService userService,RepositoryProduct repositoryProduct,
                              RepositoryShoppingCarProduct repositoryShoppingCarProduct){
        this.repositoryShoppingCar = repositoryShoppingCar;
        this.userService = userService;
        this.repositoryProduct = repositoryProduct;
        this.repositoryShoppingCarProduct = repositoryShoppingCarProduct;
    }

    public DtoUser createDtoUser(UserEntity user){
        return DtoUser.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .dateCreated(user.getDateCreated())
                .birthday(user.getBirthday()).build();
    }

    public void addProduct(Long idProduct,Long idShoppingCart,Integer amountProduct ){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCart).orElseThrow(ExceptionNotFoundCarShopping::new);
        ProductEntity product = repositoryProduct.findById(idProduct).orElseThrow(ExceptionNotFoundProduct::new);

        //validate if product already exist in the shopping cart
        Optional<ShoppingCarProductEntity> productAlreadyAddInCart = shoppingCar.getProducts().stream().filter(p -> p.getProduct().getId().equals(idProduct)).findFirst();
        if(productAlreadyAddInCart.isPresent()){
            Integer amount = productAlreadyAddInCart.get().getAmount();

            //increase product quantity
            if(!amountProduct.equals(amount)){
                productAlreadyAddInCart.get().setAmount(amountProduct);
                repositoryShoppingCarProduct.save(productAlreadyAddInCart.get());
            }
        }else{
            ShoppingCarProductEntity shoppingCarProductEntity = new ShoppingCarProductEntity();
            shoppingCarProductEntity.setProduct(product);
            shoppingCarProductEntity.setShoppingCar(shoppingCar);
            shoppingCarProductEntity.setAmount(amountProduct);
            repositoryShoppingCarProduct.save(shoppingCarProductEntity);

            shoppingCar.addProductToCart(shoppingCarProductEntity);
        }


    }

    public void create(DtoCreateShoppingCar newShoppingCar){
        userService.findUserById(newShoppingCar.getUser().getId()).orElseThrow(ExceptionNotUserFound::new);

        ShoppingCarEntity shoppingCar = new ShoppingCarEntity();
        shoppingCar.setUser(newShoppingCar.getUser());
        repositoryShoppingCar.save(shoppingCar);
    }

    public DtoShoppingCar get(Long idShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);

        List<DtoShoppingCarProduct> products =  shoppingCar.getProducts().stream().map(p -> new DtoShoppingCarProduct(
                p.getId(),new DtoProduct(
                        p.getId(),
                        p.getProduct().getName(),
                        p.getProduct().getPrice(),
                        p.getProduct().getDateCreated(),
                        p.getProduct().getDeletionDate(),
                        p.getProduct().getDicount(),
                        p.getProduct().getStock()
                )
                ,p.getAmount())).collect(Collectors.toList());

        return new DtoShoppingCar(
                shoppingCar.getId(),
                createDtoUser(shoppingCar.getUser()),
                products
        );
    }

    public void buyProducts(Long idShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);

        shoppingCar.getProducts().stream().forEach(p -> {
            if (!repositoryProduct.existsById(p.getProduct().getId())) {
                throw new ExceptionNotFoundProductToBuy(p.getProduct().getId().toString());
            }
            if (p.getAmount() > p.getProduct().getStock()) {
                throw new ExceptionInvalidQuantity(p.getProduct().getId().toString());
            }
        });
        //simulation delete all products  falta restar stock
        shoppingCar.setProducts(new ArrayList<>());
        repositoryShoppingCar.save(shoppingCar);
    }

    public DtoShoppingCar getAll(Long idShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);
        DtoShoppingCar dtoShoppingCar = DtoShoppingCar.builder()
                .id(shoppingCar.getId())
                .user(createDtoUser(shoppingCar.getUser())).build();

        return dtoShoppingCar;
    }

    public DtoShoppingCar update(DtoShoppingCar newDataShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(newDataShoppingCar.getId()).orElseThrow(ExceptionNotFoundCarShopping::new);
        UserEntity user = userService.findUserById(newDataShoppingCar.getUser().getId()).orElseThrow(ExceptionNotUserFound::new);

        shoppingCar.setId(newDataShoppingCar.getId());
        shoppingCar.setUser(user);
        repositoryShoppingCar.save(shoppingCar);
        return newDataShoppingCar;
    }

    public void deleteProductFromShoppingCar(Long idShoppingCar,Long idCarProduct){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);
        ShoppingCarProductEntity product = shoppingCar.getProducts().stream().filter(p -> p.getId().equals(idCarProduct)).findFirst()
                .orElseThrow(ExceptionNotfoundProductFromShoppingCar::new);

        repositoryShoppingCarProduct.delete(product);
    }
}
