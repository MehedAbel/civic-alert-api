package com.example.CivicAlert.repository;

import com.example.CivicAlert.model.report.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Integer> {
    List<ReportModel> findAllByUser_Email(String email);
}
