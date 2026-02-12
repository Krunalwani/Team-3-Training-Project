package com.team3.capstone.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
	@GetMapping("/test")
    public String testApi() {
        return "Application is working successfully!";
    }
}
