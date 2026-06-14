package com.banking.microservices.authservice.repository;

import com.banking.microservices.authservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
