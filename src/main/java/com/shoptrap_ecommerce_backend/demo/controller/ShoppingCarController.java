package com.shoptrap_ecommerce_backend.demo.controller;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoShoppingCar;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.service.ShoppingCarService;
import com.shoptrap_ecommerce_backend.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCar")
public class ShoppingCarController {

    private final ShoppingCarService shoppingCarService;

    public ShoppingCarController(ShoppingCarService shoppingCarService){
        this.shoppingCarService = shoppingCarService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<DtoApiResponse> getAll(@PathVariable Long idShoppingCar){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Carrito de compra obtenido",shoppingCarService.get(idShoppingCar))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<DtoApiResponse> create(@Valid @RequestBody DtoCreateShoppingCar newShoppingCar){
        shoppingCarService.create(newShoppingCar);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Carrito de compras Creado")
        );
    }

    @PostMapping("/update")
    public ResponseEntity<DtoApiResponse> updateUser(@Valid @RequestBody DtoShoppingCar newDataShppingCar){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Carrito de compras actualizado", shoppingCarService.update(newDataShppingCar))
        );
    }


    @DeleteMapping("/delete/{idShoppingCar}")
    public ResponseEntity<DtoApiResponse> delete(@PathVariable Long idShoppingCar){
        shoppingCarService.delete(idShoppingCar);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Carrtio de compras eliminado")
        );
    }
}
