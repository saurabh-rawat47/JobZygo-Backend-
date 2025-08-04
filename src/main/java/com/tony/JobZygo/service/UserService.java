package com.tony.JobZygo.service;

import com.tony.JobZygo.entity.User;
import com.tony.JobZygo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager auth;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void signup(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(user.getFirstName(),
                user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getFirstName());
        } else {
            return "Fail";
        }
    }

}
