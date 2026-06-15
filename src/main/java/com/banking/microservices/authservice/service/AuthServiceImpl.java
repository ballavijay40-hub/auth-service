package com.banking.microservices.authservice.service;

import com.banking.microservices.authservice.dto.AuthResponse;
import com.banking.microservices.authservice.dto.LoginRequest;
import com.banking.microservices.authservice.dto.RegisterRequest;
import com.banking.microservices.authservice.entity.Role;
import com.banking.microservices.authservice.entity.User;
import com.banking.microservices.authservice.repository.RoleRepository;
import com.banking.microservices.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final   UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse register(RegisterRequest req){

        //check if user already existes
        if(userRepository.existsByUsername(req.getUsername())){
            throw new RuntimeException("user already exists.");
        }

        //check if email exists

        if(userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("email already registered");
        }


        //getting teh role

        Role userRole=roleRepository.findByName("ROLE_USER")
                .orElseThrow(()->new RuntimeException("ROLE_USER not found."));

        //insert data
        User user=User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .roles(Set.of(userRole))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .username(user.getUsername())
                .roles(
                        user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())

                )
                .build();






    }

    @Override
    public AuthResponse login(LoginRequest req){
        User user=userRepository.findByUsername(req.getName())
                .orElseThrow(()->new RuntimeException("Inavalid uername or password."));

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid username or password.");
        }

        return AuthResponse.builder()

                .username(user.getUsername())
                .roles(
                        user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public void roleAssignToUser(String username,String rolename){
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found."));

        Role role=roleRepository.findByName(rolename)
                .orElseThrow(()->new RuntimeException("Role not found."));

        user.getRoles().add(role);

        userRepository.save(user);


    }


}
