package com.ntnt.chatapp.repositories;

import com.ntnt.chatapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public Optional<UserEntity> findByUsername(String username);
}
