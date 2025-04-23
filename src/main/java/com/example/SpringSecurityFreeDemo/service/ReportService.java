package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.dto.report.CreateReportDto;
import com.example.SpringSecurityFreeDemo.dto.report.ReportDto;
import com.example.SpringSecurityFreeDemo.exception.auth.ReportNotFoundException;
import com.example.SpringSecurityFreeDemo.model.report.ReportModel;
import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import com.example.SpringSecurityFreeDemo.repository.ReportRepository;
import com.example.SpringSecurityFreeDemo.repository.UserRepository;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${upload.path}") // You can define this in application.properties
    private String uploadPath;

    public ReportModel createReport(CreateReportDto createReportDto, String username, MultipartFile[] images) {
        AppUser user = userRepository.findByEmail(username);
        ReportModel reportModel = mapCreateDtoToReportModel(createReportDto, user);

        if (images != null && images.length > 0) {
            List<String> imagePaths = saveImages(images);
            reportModel.setImagePaths(imagePaths);
        }

        reportRepository.save(reportModel);

        return reportModel;
    }

    private List<String> saveImages(MultipartFile[] images) {
        List<String> imagePaths = new ArrayList<>();

        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                    Path filePath = Paths.get(uploadPath, fileName);
                    Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    imagePaths.add(filePath.toString());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
                }
            }
        }

        return imagePaths;
    }

    private ReportModel mapCreateDtoToReportModel(CreateReportDto createReportDto, AppUser user) {
        ReportModel reportModel = new ReportModel();

        reportModel.setTitle(createReportDto.getTitle());
        reportModel.setDescription(createReportDto.getDescription());
        reportModel.setCategory(createReportDto.getCategory());
        reportModel.setLatitude(createReportDto.getLatitude());
        reportModel.setLongitude(createReportDto.getLongitude());
        reportModel.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        reportModel.setUser(user);

        return reportModel;
    }

    public ReportModel getReport(Integer reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new ReportNotFoundException("Report not found");
        }

        return reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    public List<ReportDto> getAllReports() {
        List<ReportModel> reportModels = new ArrayList<>(reportRepository.findAll());
        List<ReportDto> reportDtos = new ArrayList<>(List.of());

        for (ReportModel reportModel : reportModels) {
            reportDtos.add(mapReportToReportDto(reportModel));
        }

        return reportDtos;
    }

    public List<ReportDto> getAllReportsByUsername(String username) {
        List<ReportModel> reportModels = new ArrayList<>(reportRepository.findAll().stream().filter(r -> r.getUser().getUsername().equals(username)).collect(Collectors.toList()));
        List<ReportDto> reportDtos = new ArrayList<>(List.of());

        for (ReportModel reportModel : reportModels) {
            reportDtos.add(mapReportToReportDto(reportModel));
        }

        return reportDtos;
    }

    public List<ReportDto> getAllReportsByCategory(String category) {
        List<ReportModel> reportModels = new ArrayList<>(reportRepository.findAll().stream().filter(r -> r.getCategory().equals(category)).collect(Collectors.toList()));
        List<ReportDto> reportDtos = new ArrayList<>(List.of());

        for (ReportModel reportModel : reportModels) {
            reportDtos.add(mapReportToReportDto(reportModel));
        }

        return reportDtos;
    }

    private ReportDto mapReportToReportDto(ReportModel reportModel) {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(reportModel.getId());
        reportDto.setTitle(reportModel.getTitle());
        reportDto.setDescription(reportModel.getDescription());
        reportDto.setCategory(reportModel.getCategory());
        reportDto.setLatitude(reportModel.getLatitude());
        reportDto.setLongitude(reportModel.getLongitude());
        reportDto.setCreatedAt(reportModel.getCreatedAt());
        reportDto.setStatus(reportModel.getStatus());
        reportDto.setUsername(reportModel.getUser().getUsername());

        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        List<String> fullImageUrls = reportModel.getImagePaths().stream().map(path -> baseUrl + "/uploads/" + Paths.get(path).getFileName().toString()).collect(Collectors.toList());

        reportDto.setImageUrls(fullImageUrls);

        return reportDto;
    }

    public String deleteReport(Integer reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new ReportNotFoundException("Report not found");
        }

        reportRepository.deleteById(reportId);
        return "Report deleted successfully";
    }
}
