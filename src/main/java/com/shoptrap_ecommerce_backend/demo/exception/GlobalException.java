package com.shoptrap_ecommerce_backend.demo.exception;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoApiResponse> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String messageError = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),messageError
        ));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<DtoApiResponse> BindException(BindException ex){
        String messageError = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),messageError
        ));
    }

    @ExceptionHandler(ExceptionNotUserFound.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotUserFound(ExceptionNotUserFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró el usuario a actualizar"
        ));
    }

    @ExceptionHandler(ExceptionUsernameAlreadyInUse.class)
    public ResponseEntity<DtoApiResponse> ExceptionUsernameAlreadyInUse(ExceptionUsernameAlreadyInUse ex){
        return ResponseEntity.badRequest().body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),"El username ya esta en uso"
        ));
    }

    @ExceptionHandler(ExceptionEmailAlreadyInUse.class)
    public ResponseEntity<DtoApiResponse> ExceptionEmailAlreadyInUse(ExceptionEmailAlreadyInUse ex){
        return ResponseEntity.badRequest().body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),"El email ya esta en uso"
        ));
    }

    @ExceptionHandler(ExceptionNameCategoryAlreadyExist.class)
    public ResponseEntity<DtoApiResponse> ExceptionNameCategoryAlreadyExist(ExceptionNameCategoryAlreadyExist ex){
        return ResponseEntity.badRequest().body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),"El nombre de categoria ya existe"
        ));
    }

    @ExceptionHandler(ExceptionNotFoundCategory.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotFoundCategory(ExceptionNotFoundCategory ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró la categoria para eliminarla"
        ));
    }


    @ExceptionHandler(ExceptionNotFoundProduct.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotFoundProduct(ExceptionNotFoundProduct ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró el producto para eliminarlo"
        ));
    }

    @ExceptionHandler(ExceptionNotFoundCarShopping.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotFoundCarShopping(ExceptionNotFoundCarShopping ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró el carrito de compras"
        ));
    }

    @ExceptionHandler(ExceptionNotfoundProductFromShoppingCar.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotfoundProductFromShoppingCar(ExceptionNotfoundProductFromShoppingCar ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró el producto del carrito para eliminarlo"
        ));
    }

    @ExceptionHandler(ExceptionNotFoundProductToBuy.class)
    public ResponseEntity<DtoApiResponse> ExceptionNotProductToBuy(ExceptionNotFoundProductToBuy ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoApiResponse(
                HttpStatus.NOT_FOUND.value(),"No se encontró el producto para comprar id del producto: " + ex.getMessage()
        ));
    }

    @ExceptionHandler(ExceptionInvalidQuantity.class)
    public ResponseEntity<DtoApiResponse> ExceptionInvalidQuantity(ExceptionInvalidQuantity ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoApiResponse(
                HttpStatus.BAD_REQUEST.value(),"No hay stock del producto suficiente para comprar. id del producto: " + ex.getMessage()
        ));
    }


}
