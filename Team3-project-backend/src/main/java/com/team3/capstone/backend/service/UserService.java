package com.team3.capstone.backend.service;

import java.util.List;
import java.util.Optional;

import com.team3.capstone.backend.dto.RegisterRequestDTO;
import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.Role;

public interface UserService {


    // Create or update a user
    User saveUser(User user);

    // Find a user by ID
    Optional<User> getUserById(Long userId);

    // Find a user by email
    Optional<User> getUserByEmail(String email);

    // Get all users
    List<User> getAllUsers();

    // Delete a user by ID
    void deleteUserById(Long userId);

    // Update user details
    User updateUser(Long userId, User userDetails);

    // Get users by role
    List<User> getUsersByRole(Role role);

	String registerUser(RegisterRequestDTO request);
}