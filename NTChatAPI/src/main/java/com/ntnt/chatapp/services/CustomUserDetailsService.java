package com.ntnt.chatapp.services;

import com.ntnt.chatapp.models.entities.UserEntity;
import com.ntnt.chatapp.models.system.CustomUserDetails;
import com.ntnt.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        Optional<CustomUserDetails> userDetailsOpt = userOpt.map(CustomUserDetails::new);
        return userDetailsOpt.orElse(null);
    }
}
