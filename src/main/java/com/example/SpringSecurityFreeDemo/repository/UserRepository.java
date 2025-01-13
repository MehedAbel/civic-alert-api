package com.example.SpringSecurityFreeDemo.repository;

import com.example.SpringSecurityFreeDemo.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String email);
    boolean existsByEmail(String email);
}
