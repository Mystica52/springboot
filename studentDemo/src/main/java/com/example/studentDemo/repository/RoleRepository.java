package com.example.studentDemo.repository;

import com.example.studentDemo.model.Role;
import com.example.studentDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
