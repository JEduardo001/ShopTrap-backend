package com.shoptrap_ecommerce_backend.demo.security.jwt;

import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import com.shoptrap_ecommerce_backend.demo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl( UserService userService){
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username){
        UserEntity user = userService.findUserByUsername(username);

        List<SimpleGrantedAuthority> authorityList = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .toList();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }
}
