package com.ntnt.chatapp.services;

import com.ntnt.chatapp.entities.UserEntity;
import com.ntnt.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepo;

    public Optional<UserEntity> getUser(long id){
        Optional<UserEntity> userOpt = userRepo.findById(id);
        return userOpt;
    }

    public Optional<UserEntity> getUser(String username){
        Optional<UserEntity> userOpt = userRepo.findByUsername(username);

        return userOpt;
    }

    public UserEntity addUser(UserEntity user){
        UserEntity newUser = userRepo.save(user);

        return newUser;
    }

}
