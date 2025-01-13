package com.example.SpringSecurityFreeDemo.dto;

import com.example.SpringSecurityFreeDemo.model.Role;

import java.util.Set;

public class RegisterResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;

    public RegisterResponseDto(String firstName, String lastName, String email, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public RegisterResponseDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
