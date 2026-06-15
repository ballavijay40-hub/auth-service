package com.banking.microservices.authservice.dto;

import java.util.Set;
public class AuthReposne {
    private String token;


    private String type="Bearer";

    private String username;

    private  Set<String> roles;

}
