package com.ntnt.chatapp.controlllers;

import com.ntnt.chatapp.entities.UserEntity;
import com.ntnt.chatapp.models.JwtResponse;
import com.ntnt.chatapp.models.MessageResponse;
import com.ntnt.chatapp.services.JwtService;
import com.ntnt.chatapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    private static final Logger logger = LoggerFactory.getLogger(IdentityController.class.getName());

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            JwtResponse jwt = jwtService.generateJwtResponse(authentication);

            return ResponseEntity.ok(jwt);
        }catch (InternalAuthenticationServiceException ex){
            logger.error(Arrays.toString(ex.getStackTrace()));
        }

        return new ResponseEntity<>(new MessageResponse("Username or password is incorrect",
                                                "Username not found",
                                                HttpStatus.UNAUTHORIZED.value()),
                                    HttpStatus.UNAUTHORIZED);


    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user){
        Optional<UserEntity> userOpt = userService.getUser(user.getUsername());

        if(userOpt.isPresent()){
            MessageResponse message = new MessageResponse("Sorry this username has been used",
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
