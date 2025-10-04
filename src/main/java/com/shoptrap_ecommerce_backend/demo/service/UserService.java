package com.shoptrap_ecommerce_backend.demo.service;

import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.entity.UserEntity;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryUser;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionEmailAlreadyInUse;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotUserFound;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionUsernameAlreadyInUse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private RepositoryUser repositoryUser;

    public UserService(RepositoryUser repositoryUser){
        this.repositoryUser = repositoryUser;
    }


    public Optional<UserEntity> findUserById(Long idUser){
        Optional<UserEntity> user = repositoryUser.findById(idUser);
        return user;
    }

    public void createUser(DtoCreateUser newUser){
        //hashear contrasena falta

        if(repositoryUser.findByUsername(newUser.getUsername()).isPresent()){
            throw new ExceptionUsernameAlreadyInUse();
        }

        if(repositoryUser.findByEmail(newUser.getEmail()).isPresent()){
            throw new ExceptionEmailAlreadyInUse();
        }

        UserEntity user = new UserEntity();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setSurname(newUser.getSurname());

        repositoryUser.save(user);
    }

    public List<DtoUser> getUsers(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users =  repositoryUser.findAll(pageable);

        return users.stream().map(user -> new DtoUser(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getDateCreated(),
                user.getBirthday()
        )).collect(Collectors.toList());

    }

    public DtoUser updateUser(DtoUser newDataUser, Long idUser){
        UserEntity user = repositoryUser.findById(idUser).orElseThrow(ExceptionNotUserFound::new);

        user.setId(newDataUser.getId());
        user.setName(newDataUser.getName());
        user.setEmail(newDataUser.getEmail());
        user.setUsername(newDataUser.getUsername());
        user.setSurname(newDataUser.getSurname());
        user.setBirthday(newDataUser.getBirthday());
        repositoryUser.save(user);
        return newDataUser;
    }

    public void deleteUser(Long idUser){
        UserEntity user = repositoryUser.findById(idUser).orElseThrow(ExceptionNotUserFound::new);
        repositoryUser.delete(user);
    }

}
