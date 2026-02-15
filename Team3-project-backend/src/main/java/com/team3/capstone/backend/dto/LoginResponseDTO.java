package com.team3.capstone.backend.dto;

public class LoginResponseDTO {

    private String token;
    private String role;
    private Long userId;

    // ✅ REQUIRED constructor (3 params)
    public LoginResponseDTO(String token, String role, Long userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    // getters only (no setters needed)
    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }
}
