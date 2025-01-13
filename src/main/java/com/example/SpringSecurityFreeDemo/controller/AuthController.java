package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.dto.auth.LoginDto;
import com.example.SpringSecurityFreeDemo.dto.auth.LoginResponseDto;
import com.example.SpringSecurityFreeDemo.dto.auth.RegisterDto;
import com.example.SpringSecurityFreeDemo.dto.auth.RegisterResponseDto;
import com.example.SpringSecurityFreeDemo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginUser) {
        return new ResponseEntity<>(authService.login(loginUser), HttpStatus.OK);
    }
}
