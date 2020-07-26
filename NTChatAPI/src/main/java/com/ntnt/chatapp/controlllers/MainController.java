package com.ntnt.chatapp.controlllers;

import com.ntnt.chatapp.entities.UserEntity;
import com.ntnt.chatapp.models.JwtResponse;
import com.ntnt.chatapp.services.JwtService;
import com.ntnt.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/user/{userId}")
    public @ResponseBody UserEntity showUserInfo(@PathVariable("userId") Integer userId){
        Optional<UserEntity> userOpt = userService.getUser(userId);
        userOpt.ifPresent(userEntity -> userEntity.setPassword(null));

        return userOpt.orElse(null);
    }
}
