package com.banking.microservices.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username is required.")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

}
