package com.shoptrap_ecommerce_backend.demo.controller;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import com.shoptrap_ecommerce_backend.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<DtoApiResponse> getAllUsers(@RequestParam Integer page,@RequestParam Integer size ){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuarios obtenidos",userService.getUsers(page,size))
        );
    }

    @PostMapping("/createUser")
    public ResponseEntity<DtoApiResponse> createUser(@Valid @RequestBody DtoCreateUser newUser){
        userService.createUser(newUser);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuario Creado")
        );
    }

    @PostMapping("/update/{idUser}")
    public ResponseEntity<DtoApiResponse> updateUser(@Valid @RequestBody DtoUser newDataUser, @PathVariable Long idUser){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuario actualizado", userService.updateUser(newDataUser,idUser))
        );
    }


    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<DtoApiResponse> deleteUser(@PathVariable Long idUser){
        userService.deleteUser(idUser);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuario eliminado")
        );
    }
}
