package com.team3.capstone.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.config.JwtUtil;
import com.team3.capstone.backend.dto.LoginRequestDTO;
import com.team3.capstone.backend.dto.LoginResponseDTO;
import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        // 1️ Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2️ Fetch user from database
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }



        // 3️ Generate JWT Token
        String token = jwtUtil.generateToken(user.getUsername());

        // 4️ Return response
        return ResponseEntity.ok(
                new LoginResponseDTO(token, user.getRole().name())
        );

    }
}
