package com.team3.capstone.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.Role;
import com.team3.capstone.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class AdminUserController {

    @Autowired
    private UserService userService;

    // GET ALL AGENTS
    @GetMapping("/agents")
    public List<User> getAgents() {
        return userService.getUsersByRole(Role.AGENT);
    }

    // GET ALL USERS (NORMAL USERS)
    @GetMapping("/normal-users")
    public List<User> getUsers() {
        return userService.getUsersByRole(Role.USER);
    }

}
