package com.example.demo56.repository;


import com.example.demo56.user.UserJPAEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJPAEntity, Long> {
    Optional<UserJPAEntity> findByEmail(String email);
    Optional<UserJPAEntity> findByUsernameOrEmail(String username, String email);
    Optional<UserJPAEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
