package com.example.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
public class Role {
//    @Autowired
//    private RoleRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


//    @JoinColumn(name = "user_id")





    public Role() {}

    public Role(String name) {
        this.name = name;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
//     public Optional<Role> get(Long role_id) {
//         return repo.findById(role_id);
//
//    }
}
