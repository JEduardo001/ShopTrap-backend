package com.shoptrap_ecommerce_backend.demo.security;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionEmailAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotUserFound;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionUsernameAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.security.jwt.JwtFilter;
import com.shoptrap_ecommerce_backend.demo.security.jwt.JwtService;
import com.shoptrap_ecommerce_backend.demo.security.jwt.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Controller
@EnableWebSecurity
public class GlobalSecurity {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public GlobalSecurity(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl){
        this.jwtService = jwtService;
        this.userDetailsServiceImpl =userDetailsServiceImpl;
    }

    public JwtFilter jwtFilter(){
        return new JwtFilter(jwtService,userDetailsServiceImpl);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfiguration()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated());
            return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("Content-Type","Authorization"));
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET","PUT","POST","DELETE","OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
