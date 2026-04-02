package com.ecommerce.controller;

import com.ecommerce.dto.UserResponse;
import com.ecommerce.entity.User;
import com.ecommerce.exception.InvalidRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.UserRepo;
import com.ecommerce.service.UserService;
import jakarta.ws.rs.POST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class UserController {
private static final Logger  logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<UserResponse> addUser(@RequestBody User request){

        logger.info("API CALL: Create user with email={}", request.getEmail());

        UserResponse user = userService.createUser(request);

        logger.info("User created successfully with id={}", user.getId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/users")
    public String sayHello(){
        return "hello";
    }
}
