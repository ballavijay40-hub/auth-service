package com.banking.microservices.authservice.security.service;

import com.banking.microservices.authservice.entity.User;
import com.banking.microservices.authservice.exception.UserAlreadyExistsException;
import com.banking.microservices.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new UserAlreadyExistsException("user not found"+username));


        List<SimpleGrantedAuthority> authorities=
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).toList();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }



}
