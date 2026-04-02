package com.ecommerce.service;

import com.ecommerce.dto.UserResponse;
import com.ecommerce.entity.User;
import com.ecommerce.exception.InvalidRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(User request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new InvalidRequestException(
                    "Email already registered",
                    "EMAIL_ALREADY_EXISTS"
            );
        }

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new InvalidRequestException(
                    "Username already taken",
                    "USERNAME_ALREADY_EXISTS"
            );
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_ADMIN");
        User saved =userRepo.save(user);

        return new UserResponse(user.getId(), user.getEmail(), user.getUsername());
    }
    // ✅ GET USER
    public UserResponse getUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserResponse(user.getId(), user.getEmail(), user.getUsername());
    }

}
