package com.tony.JobZygo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;

@Document(collection = "users")
public class User {
    // Getters and Setters
    @Getter
    @Setter
    @Id
    private String id;

    @Setter
    @Indexed(unique = true)
    private String username;  // Note: using userName to match your existing code

    @Getter
    @Indexed(unique = true)
    private String email;

    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String userType; // "job-seeker" or "employer"
    @Getter
    @Setter
    private LocalDateTime createdAt;

    // Default constructor
    public User(String email) {
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }


    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
