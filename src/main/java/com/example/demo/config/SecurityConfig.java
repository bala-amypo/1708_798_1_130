package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // ✅ EXPLICIT FILTER BEAN (VERY IMPORTANT)
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            CustomUserDetailsService userDetailsService
    ) {
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
