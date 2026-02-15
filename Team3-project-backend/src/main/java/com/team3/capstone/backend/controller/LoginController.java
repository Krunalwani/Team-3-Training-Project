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

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        // 1️⃣ Fetch user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Validate password (plain for now)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3️⃣ Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // 4️⃣ RETURN token + role + userId ✅
        return ResponseEntity.ok(
                new LoginResponseDTO(
                        token,
                        user.getRole().name(),
                        user.getUserId()   // 🔥 THIS FIXES EVERYTHING
                )
        );
    }
}
