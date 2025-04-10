package com.example.SpringSecurityFreeDemo.model.report;

import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String category;
    private Double latitude;
    private Double longitude;
    private Date createdAt = new Date();

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.OPEN;

    @ElementCollection
            @CollectionTable(name="report_images", joinColumns = @JoinColumn(name="report_id"))
            @Column(name="image_path")
    List<String> imagePaths = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    public ReportModel() {
    }

    public ReportModel(Integer id, String title, String description, String category, Double latitude, Double longitude, Date createdAt, ReportStatus status, List<String> imagePaths, AppUser user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.status = status;
        this.imagePaths = imagePaths;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ReportModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", imagePaths=" + imagePaths +
                ", user=" + user +
                '}';
    }
}
