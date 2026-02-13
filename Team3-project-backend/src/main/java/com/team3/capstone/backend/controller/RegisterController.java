package com.team3.capstone.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.dto.RegisterRequestDTO;
import com.team3.capstone.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO request) {

        return userService.registerUser(request);
    }
}
