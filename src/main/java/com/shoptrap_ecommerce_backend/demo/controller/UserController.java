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

    @GetMapping("/get/{idUser}")
    public ResponseEntity<DtoApiResponse> getUser(@PathVariable Long idUser){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuario obtenido",userService.findUserByIdDto(idUser))
        );
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

    @PostMapping("/update")
    public ResponseEntity<DtoApiResponse> updateUser(@Valid @RequestBody DtoUser newDataUser){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Usuario actualizado", userService.updateUser(newDataUser))
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
