package com.example.sga.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use NoOpPasswordEncoder for plain text passwords
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/register", "/login", "/css/**").permitAll() // Allow public access to these pages
                .anyRequest().authenticated() // Require authentication for all other requests
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/", true) // Redirect to home after successful login
                .permitAll() // Allow everyone to see the login page
            .and()
            .logout()
                .permitAll(); // Allow everyone to logout

        return http.build();
    }
}