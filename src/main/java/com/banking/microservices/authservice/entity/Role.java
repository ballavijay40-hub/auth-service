package com.banking.microservices.authservice.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();

}
