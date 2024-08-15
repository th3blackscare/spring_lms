package com.osharif.spring_lms.Models;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//Contains details like ID, name, contact information, etc.
@Entity
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unsigned
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "patron")
    private List<Borrow> borrowList;
    private boolean isActive = true;

    public Patron() {}

    public Patron( String firstName, String lastName, String address, String phone, String email, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
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
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return isActive == patron.isActive &&
                Objects.equals(firstName, patron.firstName) &&
                Objects.equals(lastName, patron.lastName) &&
                Objects.equals(phone, patron.phone) &&
                Objects.equals(address, patron.address) &&
                Objects.equals(email, patron.email) &&
                Objects.equals(birthDate, patron.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, email, birthDate, address, isActive);
    }
}
