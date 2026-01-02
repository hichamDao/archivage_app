package com.archivage.demo.controller;


import com.archivage.demo.model.User;
import com.archivage.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
public AuthController(UserService userService){
    this.userService=userService;
}

    @PostMapping("/register")
public User register(@RequestBody Map<String,String> body){
    return userService.register(body.get("username"), body.get("email"), body.get("password"));
}



    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body){
        Optional<User> useropt = userService.findByEmail(body.get("email"));
        if(useropt.isPresent() && userService.checkPassword(useropt.get(), body.get("password"))){

            return "User logged in";
        }
        return "Invalid credentials";
    }

    }
