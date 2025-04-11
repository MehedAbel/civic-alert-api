package com.example.SpringSecurityFreeDemo.dto.report;

import com.example.SpringSecurityFreeDemo.model.report.ReportStatus;

import java.util.Date;
import java.util.List;

public class ReportDto {
    private int id;
    private String title;
    private String description;
    private String category;
    private Double latitude;
    private Double longitude;
    private List<String> imageUrls;

    private String username;
    private Date createdAt;
    private ReportStatus status;

    public ReportDto() {
    }

    public ReportDto(int id, String title, String description, String category, Double latitude, Double longitude, List<String> imageUrls, String username, Date createdAt, ReportStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrls = imageUrls;
        this.username = username;
        this.createdAt = createdAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imageUrls=" + imageUrls +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
