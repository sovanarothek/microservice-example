package com.example.authservice.controller;

import com.example.authservice.request.LoginRequest;
import com.example.authservice.request.RegisterRequest;
import com.example.authservice.service.AuthService;
import com.example.common.base.util.ApiResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth Service", description = "Auth Service for login and register")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ApiResult> login(@Validated @RequestBody LoginRequest request) throws Exception {
        ApiResult response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("register")
    public ResponseEntity<ApiResult> register(@Validated @RequestBody RegisterRequest request) {
        ApiResult response = authService.register(request);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
