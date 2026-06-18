package com.banking.microservices.authservice.dto;

import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;

    @Builder.Default
    private String type="Bearer";

    private String username;

    private List<String> roles;

}
