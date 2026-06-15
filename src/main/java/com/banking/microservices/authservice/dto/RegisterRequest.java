package com.banking.microservices.authservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "user name is required.")
    @Size(
            min=3,
            max=30,
            message = "username must be between 3 and  30 characters."

    )
    private String username;

    @NotBlank(message = "Email is required.")
    @Email
    @Size(
            max=100,
            message = "Email cannot exceed 100 characters"
    )
    private String email;


    @NotBlank(message = "passsword is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a number, and a special character."
    )
    private String password;


}
