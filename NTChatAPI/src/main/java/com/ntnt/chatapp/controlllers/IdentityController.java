package com.ntnt.chatapp.controlllers;

import com.ntnt.chatapp.entities.UserEntity;
import com.ntnt.chatapp.models.JwtResponse;
import com.ntnt.chatapp.models.Message;
import com.ntnt.chatapp.services.JwtService;
import com.ntnt.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class IdentityController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtResponse jwt = jwtService.generateJwtResponse(authentication);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user){
        Optional<UserEntity> userOpt = userService.getUser(user.getUsername());

        if(userOpt.isPresent()){
            Message message = new Message("Sorry this username has been used",
                                            "Database: username is a unique field",
                                            20001);
            return new ResponseEntity<>(message, HttpStatus.SEE_OTHER);
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity newUser = userService.addUser(user);

            return ResponseEntity.ok(newUser);
        }
    }
}
