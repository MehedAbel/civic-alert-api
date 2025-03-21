package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;


}
