package com.example.CivicAlert.controller;

import com.example.CivicAlert.model.DemoModel;
import com.example.CivicAlert.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping
    public ResponseEntity<DemoModel> testDemo(@RequestBody DemoModel demoModel) {
        return new ResponseEntity<>(demoService.createDemo(demoModel), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<List<DemoModel>> getDemos() {
        return new ResponseEntity<>(demoService.getDemos(), HttpStatus.OK);
    }
}
