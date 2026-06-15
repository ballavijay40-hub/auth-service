package com.banking.microservices.authservice.controller;


import com.banking.microservices.authservice.dto.AuthResponse;
import com.banking.microservices.authservice.dto.LoginRequest;
import com.banking.microservices.authservice.dto.RegisterRequest;
import com.banking.microservices.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> rigister(@Valid @RequestBody RegisterRequest req){
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/assign-role")
    public ResponseEntity<String> assignRole(
            @RequestParam String username,
            @RequestParam String role){
        authService.roleAssignToUser(username,role);
        return ResponseEntity.ok("Role assigned successfully.");
    }

}
