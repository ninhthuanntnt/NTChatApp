package com.ntnt.chatapp.services;

import com.ntnt.chatapp.models.entities.RoleEntity;
import com.ntnt.chatapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public List<RoleEntity> getAllRoles(){
        return roleRepo.findAll();
    }
}
