package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.exception.auth.ReportNotFoundException;
import com.example.SpringSecurityFreeDemo.model.report.ReportModel;
import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import com.example.SpringSecurityFreeDemo.repository.ReportRepository;
import com.example.SpringSecurityFreeDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    public ReportModel createReport(CreateReportDto createReportDto, String username) {
        AppUser user = userRepository.findByEmail(username);
        ReportModel reportModel = mapCreateDtoToReportModel(createReportDto, user);
        reportRepository.save(reportModel);

        return reportModel;
    }

    private ReportModel mapCreateDtoToReportModel(CreateReportDto createReportDto, AppUser user) {
        ReportModel reportModel = new ReportModel();

        reportModel.setTitle(createReportDto.getTitle());
        reportModel.setDescription(createReportDto.getDescription());
        reportModel.setCategory(createReportDto.getCategory());
        reportModel.setLatitude(createReportDto.getLatitude());
        reportModel.setLongitude(createReportDto.getLongitude());
        reportModel.setUser(user);

        return reportModel;
    }

    public ReportModel getReport(Integer reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new ReportNotFoundException("Report not found");
        }

        return reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    public List<ReportModel> getAllReports() {
        return reportRepository.findAll();
    }

    public List<ReportModel> getAllReportsByUsername(String username) {
        return reportRepository.findAll().stream().filter(r -> r.getUser().getUsername().equals(username)).collect(Collectors.toList());
    }

    public List<ReportModel> getAllReportsByCategory(String category) {
        return reportRepository.findAll().stream().filter(r -> r.getCategory().equals(category)).collect(Collectors.toList());
    }

    public String deleteReport(Integer reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new ReportNotFoundException("Report not found");
        }

        reportRepository.deleteById(reportId);
        return "Report deleted successfully";
    }
}
