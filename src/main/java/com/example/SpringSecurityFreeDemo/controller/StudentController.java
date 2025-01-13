package com.example.SpringSecurityFreeDemo.controller;

import com.example.SpringSecurityFreeDemo.model.Role;
import com.example.SpringSecurityFreeDemo.model.Student;
import com.example.SpringSecurityFreeDemo.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController {
    @Autowired
    private JWTService jwtService;

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Sam", 55),
            new Student(2, "Frodo", 62)
    ));

    @Secured("ROLE_USER")
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping("/test")
    public Set<Role> test(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring("Bearer ".length());

        var username = jwtService.extractUsername(token);
        var roles = jwtService.extractRoles(token);

        return roles;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/demo")
    public String demo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> something = auth.getAuthorities();
        something.forEach(System.out::println);

        return auth.getName();
    }
}
