package com.example.SpringSecurityFreeDemo.repository;

import com.example.SpringSecurityFreeDemo.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Integer> {
}
