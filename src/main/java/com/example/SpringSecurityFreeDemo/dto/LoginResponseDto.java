package com.example.SpringSecurityFreeDemo.dto;

import com.example.SpringSecurityFreeDemo.model.Role;

import java.util.Set;

public class LoginResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String accessToken;
    private Set<Role> roles;

    public LoginResponseDto(String firstName, String lastName, String email, String accessToken, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accessToken = accessToken;
        this.roles = roles;
    }

    public LoginResponseDto() {
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
