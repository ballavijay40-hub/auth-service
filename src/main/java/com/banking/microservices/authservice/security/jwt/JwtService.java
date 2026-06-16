package com.banking.microservices.authservice.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

        //geneate the singning key
    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    //generate token


}
