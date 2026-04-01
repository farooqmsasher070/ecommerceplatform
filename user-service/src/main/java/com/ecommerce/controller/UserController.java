package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepo;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/AddUser")
    public String addUser(@RequestBody User user){
        userRepo.save(user);
        return "user added";
    }

    @GetMapping("/users")
    public String sayHello(){
        return "hello";
    }
}
