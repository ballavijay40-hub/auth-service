package com.banking.microservices.authservice.config;

import com.banking.microservices.authservice.entity.Role;
import com.banking.microservices.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args){
        seedRoleIfNotExists("ROLE_USER");
        seedRoleIfNotExists("ROLE_ADMIN");
        seedRoleIfNotExists("ROLE_MANAGER");

    }

    public void seedRoleIfNotExists(String name){
        boolean exists=roleRepository.existsByName(name);

        if(!exists){
            Role role= Role.builder()
                    .name(name)
                    .build();

            roleRepository.save(role);


        }


    }
}
