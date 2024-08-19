package com.ebdesk.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "Protected Resource")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/")
    public ResponseEntity<?> getResources() {
        return ResponseEntity.ok("Protected resource");
    }
}
