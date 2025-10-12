package com.shoptrap_ecommerce_backend.demo.controller.auth;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponseWithToken;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthController(PasswordEncoder passwordEncoder,AuthService authService){
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/login/{username}/{password}")
    public ResponseEntity<DtoApiResponseWithToken> login(@PathVariable String username, @PathVariable String password){
       return ResponseEntity.status(HttpStatus.OK).body(new DtoApiResponseWithToken(
                HttpStatus.OK.value(),authService.login(username,password)
               )
       );
    }

    @PostMapping("/register")
    public ResponseEntity<DtoApiResponse> register(@Valid @RequestBody DtoCreateUser newUserData){
        authService.register(newUserData);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoApiResponse<>(
                        HttpStatus.OK.value(),"Usuario Registrado"
                )
        );
    }
}
