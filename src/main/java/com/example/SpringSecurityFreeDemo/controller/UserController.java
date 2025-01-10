package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.dto.LoginDto;
import com.example.SpringSecurityFreeDemo.dto.LoginResponseDto;
import com.example.SpringSecurityFreeDemo.dto.RegisterDto;
import com.example.SpringSecurityFreeDemo.dto.RegisterResponseDto;
import com.example.SpringSecurityFreeDemo.model.Users;
import com.example.SpringSecurityFreeDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(service.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginUser) {
        return new ResponseEntity<>(service.login(loginUser), HttpStatus.OK);
    }
}
