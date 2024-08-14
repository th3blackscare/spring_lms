package com.osharif.spring_lms.DTO;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Contains details like ID, name, contact information, etc.
public class patronDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private Boolean isActive;

    public patronDTO(String firstName, String lastName, String address, String phone, String email, String birthDate, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        setBirthDate(birthDate);
        this.isActive = isActive;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(String birthDate) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthDate = LocalDate.parse(birthDate, dtf);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {}
}
