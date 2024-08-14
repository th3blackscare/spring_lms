package com.osharif.spring_lms.Models;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Unsigned
    private long id;
    private long bookId;
    private long userId;
    private long patronId;
    private LocalDate borrowDate = LocalDate.now();
    private LocalDate returnDate;

    public Borrow() {}

    public Borrow( long bookId, long userId, long patronId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.patronId = patronId;
//        this.borrowDate = borrowDate;
//        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public long getUserId() {
        return userId;
    }

    public long getPatronId() {
        return patronId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPatronId(long patronId) {
        this.patronId = patronId;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return bookId == borrow.bookId &&
                Objects.equals(userId, borrow.userId) &&
                Objects.equals(patronId, borrow.patronId) &&
                Objects.equals(borrowDate, borrow.borrowDate) &&
                Objects.equals(returnDate, borrow.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, userId, patronId, borrowDate, returnDate);
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", patronId=" + patronId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
