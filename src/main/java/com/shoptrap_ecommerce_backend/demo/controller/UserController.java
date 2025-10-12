package com.shoptrap_ecommerce_backend.demo.controller;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import com.shoptrap_ecommerce_backend.demo.service.AuthService;
import com.shoptrap_ecommerce_backend.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;


    public UserController(UserService userService,AuthService authService){
        this.userService = userService;
        this.authService = authService;
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
