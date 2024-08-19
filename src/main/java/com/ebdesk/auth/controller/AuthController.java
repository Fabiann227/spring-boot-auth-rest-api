package com.ebdesk.auth.controller;

import com.ebdesk.auth.jwt.JwtResponse;
import com.ebdesk.auth.dto.LoginRequest;
import com.ebdesk.auth.dto.RegisterRequest;
import com.ebdesk.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "User Register")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        authService.registerUser(request.getUsername(), request.getPassword(), request.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
