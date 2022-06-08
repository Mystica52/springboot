package com.example.demo56.repository;

import com.example.demo56.user.RoleJPAEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleJPAEntity, Long> {
    Optional<RoleJPAEntity> findByName(String name);
}
