package com.example.SpringSecurityFreeDemo.repository;

import com.example.SpringSecurityFreeDemo.model.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Integer> {
}
