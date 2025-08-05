package com.tony.JobZygo.service;

import com.tony.JobZygo.entity.User;
import com.tony.JobZygo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager auth;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User signup(User user) {
        try {
            System.out.println("UserService.signup called for: " + user.getUsername());
            System.out.println("User email: " + user.getEmail());
            System.out.println("User type: " + user.getUserType());

            // Validate input
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                throw new RuntimeException("Username is required");
            }
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                throw new RuntimeException("Email is required");
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                throw new RuntimeException("Password is required");
            }
            if (user.getUserType() == null || user.getUserType().trim().isEmpty()) {
                throw new RuntimeException("User type is required");
            }

            // Check if username already exists
            Optional<Optional<User>> existingUserByUsername =
                    Optional.ofNullable(userRepo.findByUsername(user.getUsername()));
            if (existingUserByUsername.isPresent()) {
                throw new RuntimeException("Username already exists");
            }

            // Check if email already exists (if you have this method in your repo)
            try {
                Optional<User> existingUserByEmail = userRepo.findByEmail(user.getEmail());
                if (existingUserByEmail.isPresent()) {
                    throw new RuntimeException("Email already exists");
                }
            } catch (Exception e) {
                // If findByEmail method doesn't exist, skip this check
                System.out.println("Email uniqueness check skipped - method not available");
            }

            // Hash the password
            user.setPassword(encoder.encode(user.getPassword()));

            // Save the user
            User savedUser = userRepo.save(user);
            System.out.println("User saved successfully with ID: " + savedUser.getId());

            return savedUser;

        } catch (RuntimeException e) {
            System.err.println("Signup error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Signup exception: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Signup failed: " + e.getMessage());
        }
    }

    public String verify(User user) {
        try {
            System.out.println("UserService.verify called for: " + user.getUsername());

            Authentication authentication = auth.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                System.out.println("Authentication successful for user: " + user.getUsername());
                return token;
            } else {
                System.err.println("Authentication failed for user: " + user.getUsername());
                throw new RuntimeException("Invalid username or password");
            }

        } catch (AuthenticationException e) {
            System.err.println("Authentication exception for user " + user.getUsername() + ": " + e.getMessage());
            throw new RuntimeException("Invalid username or password");
        } catch (Exception e) {
            System.err.println("Verify exception: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
