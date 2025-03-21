package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import com.example.SpringSecurityFreeDemo.service.DemoService;
import com.example.SpringSecurityFreeDemo.service.ReportService;
import com.example.SpringSecurityFreeDemo.service.user.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping
    public ResponseEntity<CreateReportDto> createReport(@RequestBody CreateReportDto createReportDto) {


        return new ResponseEntity<>(reportService.createReport(createReportDto, 2), HttpStatus.CREATED);
    }
}
