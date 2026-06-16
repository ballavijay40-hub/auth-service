package com.banking.microservices.authservice.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

        //geneate the singning key
    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    //generate token
    public String generateToken(String username,Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiration()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();



    }

//    //extract username
//    public String extractUsername(String token) {
//        return getClaims(token).getSubject();
//    }

    //validate token
    public boolean isTokenExpired(String token,String username){
        String extractedUsername=getClaims(token).getSubject();
        return extractedUsername.equals(username)
                && !isTokenExpired(token, username);
    }







    //extract allclaims
    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
