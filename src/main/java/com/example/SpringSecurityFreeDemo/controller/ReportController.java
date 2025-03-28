package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.model.report.ReportModel;
import com.example.SpringSecurityFreeDemo.service.ReportService;
import com.example.SpringSecurityFreeDemo.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportModel> getReport(@PathVariable Integer reportId) {
        return new ResponseEntity<>(reportService.getReport(reportId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportModel>> getAllReports() {
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

    @GetMapping("/all-by-username/{username}")
    public ResponseEntity<List<ReportModel>> getAllReportsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(reportService.getAllReportsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/all-by-category/{category}")
    public ResponseEntity<List<ReportModel>> getAllReportsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(reportService.getAllReportsByCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Integer reportId) {
        return new ResponseEntity<>(reportService.deleteReport(reportId), HttpStatus.OK);
    }

}
