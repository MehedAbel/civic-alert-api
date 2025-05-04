package com.example.CivicAlert.controller;

import com.example.CivicAlert.dto.report.CreateReportDto;
import com.example.CivicAlert.dto.report.ReportDto;
import com.example.CivicAlert.model.report.ReportModel;
import com.example.CivicAlert.service.ReportService;
import com.example.CivicAlert.service.auth.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ReportModel> createReport(@RequestPart("report") String reportJson,
                                                    @RequestPart(value = "images", required = false) MultipartFile[] images,
                                                    @AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {

        String username = userDetails.getUsername();

        ObjectMapper mapper = new ObjectMapper();
        CreateReportDto createReportDto = mapper.readValue(reportJson, CreateReportDto.class);

        return new ResponseEntity<>(reportService.createReport(createReportDto, username, images), HttpStatus.CREATED);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportModel> getReport(@PathVariable Integer reportId) {
        return new ResponseEntity<>(reportService.getReport(reportId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

    @GetMapping("/all-by-username/{username}")
    public ResponseEntity<List<ReportDto>> getAllReportsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(reportService.getAllReportsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/all-by-category/{category}")
    public ResponseEntity<List<ReportDto>> getAllReportsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(reportService.getAllReportsByCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Integer reportId) {
        return new ResponseEntity<>(reportService.deleteReport(reportId), HttpStatus.OK);
    }

}
