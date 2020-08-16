package com.ntnt.chatapp.services;

import com.ntnt.chatapp.models.entities.UserEntity;
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

    public String getExistedUserField(UserEntity user){
        Optional<UserEntity> userOpt = userRepo.findByUsernameOrEmailOrPhoneNumber(
                                                        user.getUsername(),
                                                        user.getEmail(),
                                                        user.getPhoneNumber());

        return userOpt.map(userEntity -> {
            if(userEntity.getUsername().equals(user.getUsername()))
                return user.getUsername();
            else if(userEntity.getEmail().equals(user.getEmail()))
                return user.getEmail();
            else if(userEntity.getPhoneNumber().equals(user.getPhoneNumber()))
                return user.getPhoneNumber();
            else
                return null;
        }).orElse(null);
    }
}
