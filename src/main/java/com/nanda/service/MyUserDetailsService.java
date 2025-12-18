package com.nanda.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import
org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override

    public UserDetails loadUserByUsername(String username)

    throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
        .username("admin")
        .password("$2y$10$Mv1g5boZ4Dz8/sRhtzvtxuU5IUG3EHzT7tSqT/lOg8XJEOAUwj1o6")
        .roles("ADMIN")
        .build();
    }    
}
