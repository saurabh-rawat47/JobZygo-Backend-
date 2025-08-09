package com.tony.JobZygo.controller;

import com.tony.JobZygo.entity.User;
import com.tony.JobZygo.service.UserService;
import com.tony.JobZygo.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@CrossOrigin(origins = "http://localhost:3000")
public class OAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    // Get Google client credentials from application.properties
    @Value("${spring.security.oauth2.client.registration.google.client-id:}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret:}")
    private String googleClientSecret;

    @PostMapping("/google")
    public ResponseEntity<Map<String, Object>> googleAuth(@RequestBody Map<String, String> request, HttpServletResponse response) {
        Map<String, Object> responseBody = new HashMap<>();

        try {
            System.out.println("=== GOOGLE OAUTH REQUEST RECEIVED ===");

            String code = request.get("code");

            if (code == null || code.trim().isEmpty()) {
                responseBody.put("success", false);
                responseBody.put("message", "Authorization code is required");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }

            System.out.println("Received authorization code: " + code.substring(0, 10) + "...");

            // For now, we'll simulate getting user info from Google
            // In a real implementation, you'd exchange the code for an access token
            // and then get user info from Google's API

            // Simulate user info (replace with actual Google API call)
            Map<String, Object> userInfo = simulateGoogleUserInfo(code);

            if (userInfo == null) {
                responseBody.put("success", false);
                responseBody.put("message", "Failed to get user information from Google");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }

            String email = (String) userInfo.get("email");
            String name = (String) userInfo.get("name");
            String googleId = (String) userInfo.get("id");

            System.out.println("Google user info - Email: " + email + ", Name: " + name);

            // Check if user already exists
            User existingUser = userService.findByEmail(email);
            User user;

            if (existingUser != null) {
                // User exists
                user = existingUser;
                System.out.println("Existing user found: " + user.getUsername());
            } else {
                // Create new user
                user = new User();
                user.setUsername(generateUsernameFromEmail(email));
                user.setEmail(email);
                user.setPassword("OAUTH_USER"); // OAuth users don't have passwords
                user.setUserType("JOBSEEKER"); // Default to job seeker

                try {
                    user = userService.signupOAuth(user);
                    System.out.println("New OAuth user created: " + user.getUsername());
                } catch (Exception e) {
                    System.err.println("Error creating OAuth user: " + e.getMessage());
                    responseBody.put("success", false);
                    responseBody.put("message", "Failed to create user account");
                    return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            // Generate JWT token
            String jwtToken = jwtService.generateToken(user.getUsername());

            // Set token in response header
            response.setHeader("Authorization", "Bearer " + jwtToken);

            // Prepare response
            user.setPassword(null); // Don't return password
            responseBody.put("success", true);
            responseBody.put("message", "Google authentication successful");
            responseBody.put("token", jwtToken);
            responseBody.put("user", user);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println("Google OAuth error: " + e.getMessage());
            e.printStackTrace();
            responseBody.put("success", false);
            responseBody.put("message", "Google authentication failed: " + e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Simulate getting user info from Google (replace with actual implementation)
    private Map<String, Object> simulateGoogleUserInfo(String code) {
        // In a real implementation, you would:
        // 1. Exchange the authorization code for an access token
        // 2. Use the access token to get user info from Google's API

        // For now, we'll return simulated data
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", "google_" + System.currentTimeMillis());
        userInfo.put("email", "user@gmail.com"); // This would come from Google
        userInfo.put("name", "Google User"); // This would come from Google
        userInfo.put("verified_email", true);

        return userInfo;
    }

    private String generateUsernameFromEmail(String email) {
        // Generate a username from email
        String username = email.split("@")[0].replaceAll("[^a-zA-Z0-9]", "");
        return username.toLowerCase() + "_" + System.currentTimeMillis() % 10000;
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OAuth service is running");
        response.put("google_client_configured", !googleClientId.isEmpty() ? "yes" : "no");
        return ResponseEntity.ok(response);
    }
}
