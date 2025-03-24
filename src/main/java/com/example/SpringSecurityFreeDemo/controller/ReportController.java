package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.model.ReportModel;
import com.example.SpringSecurityFreeDemo.service.ReportService;
import com.example.SpringSecurityFreeDemo.service.auth.JwtService;
import com.example.SpringSecurityFreeDemo.service.user.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private JwtService jwtService;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<ReportModel> createReport(@RequestBody CreateReportDto createReportDto,
                                                    @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        return new ResponseEntity<>(reportService.createReport(createReportDto, username), HttpStatus.CREATED);
    }
}
