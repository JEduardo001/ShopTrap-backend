package com.shoptrap_ecommerce_backend.demo.exception;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionEmailAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotUserFound;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionUsernameAlreadyInUse;
import org.hibernate.exception.ConstraintViolationException;
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
}
