package com.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.repository.UserRepository;

@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Public endpoint to test DB connection
    @GetMapping("/test-db")
    public String testDb() {
        try {
            long count = userRepository.count(); // simple DB query
            return "DB connected! Users: " + count;
        } catch (Exception e) {
            return "DB connection failed: " + e.getMessage();
        }
    }
}
