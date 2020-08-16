package com.ntnt.chatapp.repositories;

import com.ntnt.chatapp.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByUsername(String username);
    public Optional<UserEntity> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);
}
