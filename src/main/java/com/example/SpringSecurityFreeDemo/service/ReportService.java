package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.model.ReportModel;
import com.example.SpringSecurityFreeDemo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public CreateReportDto createReport(CreateReportDto createReportDto, Integer userId) {


        return createReportDto;
    }

}
