package com.tony.JobZygo.controller;

import com.tony.JobZygo.entity.User;
import com.tony.JobZygo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.37:3000"})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) {
        try {
            System.out.println("Signup request received for user: " + user.getUsername());
            System.out.println("Request body: " + user.toString());

            User savedUser = userService.signup(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User created successfully");
            response.put("user", Map.of(
                    "id", savedUser.getId(),
                    "username", savedUser.getUsername(),
                    "email", savedUser.getEmail(),
                    "userType", savedUser.getUserType()
            ));

            System.out.println("User created successfully: " + savedUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            System.err.println("Signup error: " + e.getMessage());
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        } catch (Exception e) {
            System.err.println("Signup exception: " + e.getMessage());
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Signup failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        try {
            System.out.println("Login request received for user: " + user.getUsername());

            String token = userService.verify(user);

            // Get the full user details for response
            User fullUser = userService.findByUsername(user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", Map.of(
                    "id", fullUser.getId(),
                    "username", fullUser.getUsername(),
                    "email", fullUser.getEmail(),
                    "userType", fullUser.getUserType()
            ));
            response.put("token", token);

            System.out.println("Login successful for user: " + user.getUsername());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            System.err.println("Login error: " + e.getMessage());
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        } catch (Exception e) {
            System.err.println("Login exception: " + e.getMessage());
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Login failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
