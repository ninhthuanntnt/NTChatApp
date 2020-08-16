package com.ntnt.chatapp.controlllers;

import com.ntnt.chatapp.models.entities.UserEntity;
import com.ntnt.chatapp.services.RoleService;
import com.ntnt.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/user/{userId}")
    public UserEntity showUserInfo(@PathVariable("userId") Integer userId){
        Optional<UserEntity> userOpt = userService.getUser(userId);
//        userOpt.ifPresent(userEntity -> userEntity.setPassword(null));

        return userOpt.orElse(null);
    }

    @GetMapping(value = "/role")
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
