package com.banking.microservices.authservice.service;

import com.banking.microservices.authservice.dto.AuthResponse;
import com.banking.microservices.authservice.dto.LoginRequest;
import com.banking.microservices.authservice.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest req);

    AuthResponse login(LoginRequest req);

    void roleAssignToUser(String username,String rolename);

}
