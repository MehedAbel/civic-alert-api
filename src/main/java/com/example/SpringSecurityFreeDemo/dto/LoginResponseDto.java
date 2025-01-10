package com.example.SpringSecurityFreeDemo.dto;

public class LoginResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String jwtAccessToken;

    public LoginResponseDto(String firstName, String lastName, String email, String jwtAccessToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jwtAccessToken = jwtAccessToken;
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

    public String getJwtAccessToken() {
        return jwtAccessToken;
    }

    public void setJwtAccessToken(String jwtAccessToken) {
        this.jwtAccessToken = jwtAccessToken;
    }
}
