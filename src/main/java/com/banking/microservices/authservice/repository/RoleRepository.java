package com.banking.microservices.authservice.repository;

import com.banking.microservices.authservice.entity.Role;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(@NonNull  String name);
}
