package com.example.CivicAlert.repository;

import com.example.CivicAlert.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Integer> {
}
