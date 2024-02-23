package com.example.webportal.controllers;

import com.example.webportal.DTO.UserLoginDTO;
import com.example.webportal.DTO.UserRegistrationDTO;
import com.example.webportal.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/wd")
@CrossOrigin(origins = {"https://stacjapogodowa.serveo.net", "http://localhost:4200", "http://localhost:8000"})
public class UserController {


    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        logger.info("UserController initialized");
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        logger.info("Attempting to register user...");
        userService.registerUser(registrationDTO);
        logger.info("User registration successful");
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
        try {
            if (userService.validateLoginData(loginDTO)) {
                String token = userService.generateJwtToken(loginDTO.getEmail());
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}