package com.example.sga.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sga.model.User;
import com.example.sga.model.Role;
import com.example.sga.repository.RoleRepository;
import com.example.sga.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Convert roles to GrantedAuthority
            Collection<? extends GrantedAuthority> authorities = user.get().getRoles().stream()
                    .map(role -> (GrantedAuthority) () -> role.getName())
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                    user.get().getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    // Register a new user (Member or Admin)
    public User registerNewUser(User user, String roleName) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Fetch the role by name
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Set the role to the user
        user.setRoles(Collections.singleton(role));

        // Save the user
        return userRepository.save(user);
    }

    // Find a user by email (used for login)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get user by ID (useful for modifying or canceling reservations)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // List all users (useful for admin dashboards)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}