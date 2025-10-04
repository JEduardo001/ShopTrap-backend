package com.shoptrap_ecommerce_backend.demo.service;

import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.entity.ShoppingCarEntity;
import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionEmailAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundCarShopping;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotUserFound;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionUsernameAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryShoppingCar;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCarService {

    private RepositoryShoppingCar repositoryShoppingCar;
    private UserService userService;


    public ShoppingCarService(RepositoryShoppingCar repositoryShoppingCar,UserService userService){
        this.repositoryShoppingCar = repositoryShoppingCar;
        this.userService = userService;
    }

    public void create(DtoCreateShoppingCar newShoppingCar){
        userService.findUserById(newShoppingCar.getUser().getId()).orElseThrow(ExceptionNotUserFound::new);

        ShoppingCarEntity shoppingCar = new ShoppingCarEntity();
        shoppingCar.setUser(newShoppingCar.getUser());
        repositoryShoppingCar.save(shoppingCar);
    }

    public DtoShoppingCar get(Long idShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);
        return new DtoShoppingCar(
                shoppingCar.getId(),
                shoppingCar.getUser()
        );
    }

    public DtoShoppingCar update(DtoShoppingCar newDataShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(newDataShoppingCar.getId()).orElseThrow(ExceptionNotFoundCarShopping::new);

        shoppingCar.setId(newDataShoppingCar.getId());
        shoppingCar.setUser(newDataShoppingCar.getUser());
        repositoryShoppingCar.save(shoppingCar);
        return newDataShoppingCar;
    }

    public void delete(Long idShoppingCar){
        ShoppingCarEntity shoppingCar = repositoryShoppingCar.findById(idShoppingCar).orElseThrow(ExceptionNotFoundCarShopping::new);
        repositoryShoppingCar.delete(shoppingCar);
    }
}
