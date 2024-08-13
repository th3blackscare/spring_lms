package com.osharif.spring_lms.Security.Models;

public class AuthenticationRequest {

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
    private String User_Agent;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
// Getters and Setters
}

