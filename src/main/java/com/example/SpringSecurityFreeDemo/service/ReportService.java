package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.model.ReportModel;
import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import com.example.SpringSecurityFreeDemo.repository.ReportRepository;
import com.example.SpringSecurityFreeDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
