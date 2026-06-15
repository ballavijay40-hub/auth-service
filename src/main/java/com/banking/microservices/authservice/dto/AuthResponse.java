package com.banking.microservices.authservice.dto;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;


    private String type="Bearer";

    private String username;

    private  Set<String> roles;

}
