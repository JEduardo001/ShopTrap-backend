package com.shoptrap_ecommerce_backend.demo.service;

import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionEmailAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionUsernameAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import com.shoptrap_ecommerce_backend.demo.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final RepositoryUser repositoryUser;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticaionManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService,JwtService jwtService,AuthenticationManager authenticaionManager,RepositoryUser repositoryUser, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticaionManager = authenticaionManager;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username,String password){
        userService.findUserByUsername(username);
        authenticaionManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        return jwtService.createToken(username);
    }

    public void register(DtoCreateUser newUser){

        if(repositoryUser.findByUsername(newUser.getUsername()).isPresent()){
            throw new ExceptionUsernameAlreadyInUse();
        }

        if(repositoryUser.findByEmail(newUser.getEmail()).isPresent()){
            throw new ExceptionEmailAlreadyInUse();
        }

        String passwordHash = passwordEncoder.encode(newUser.getPassword());

        UserEntity user = new UserEntity();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordHash);
        user.setSurname(newUser.getSurname());

        repositoryUser.save(user);
    }
}
