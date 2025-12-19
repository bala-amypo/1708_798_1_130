package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        
        if ("test@example.com".equals(email)) {
            User user = new User(1L, email, "password");
            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
