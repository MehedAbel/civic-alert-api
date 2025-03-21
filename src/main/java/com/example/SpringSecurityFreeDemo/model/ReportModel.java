package com.example.SpringSecurityFreeDemo.model;

import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import jakarta.persistence.*;

@Entity
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;
}
