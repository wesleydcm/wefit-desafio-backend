package com.wefit.desafio_backend.infrastructure.controller;

import com.wefit.desafio_backend.application.service.UserRegistrationService;
import com.wefit.desafio_backend.domain.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class UserRegistrationController {

    private final UserRegistrationService registrationService;

    public UserRegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        registrationService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully");
    }
}