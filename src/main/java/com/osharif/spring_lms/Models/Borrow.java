package com.osharif.spring_lms.Models;

import com.osharif.spring_lms.Services.BookService;
import com.osharif.spring_lms.Services.PatronService;
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
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private long userId;
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;
    private LocalDate borrowDate = LocalDate.now();
    private LocalDate returnDate;

    public Borrow() {}

    public Borrow( long bookId, long userId, long patronId) {
        this.id = id;
        setBookId(bookId);
        this.userId = userId;
        setPatronId(patronId);
//        this.borrowDate = borrowDate;
//        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return book.getId();
    }

    public long getUserId() {
        return userId;
    }

    public long getPatronId() {
        return patron.getId();
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
        Book book1 = new Book();
        book1.setId(bookId);
        this.book = book1;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPatronId(long patronId) {
        Patron patron1 = new Patron();
        patron1.setId(patronId);
        this.patron = patron1;
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
        return book.getId() == borrow.book.getId() &&
                Objects.equals(userId, borrow.userId) &&
                Objects.equals(patron.getId(), borrow.patron.getId()) &&
                Objects.equals(borrowDate, borrow.borrowDate) &&
                Objects.equals(returnDate, borrow.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book.getId(), userId, patron.getId(), borrowDate, returnDate);
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "bookId=" + book.getId() +
                ", userId=" + userId +
                ", patronId=" + patron.getId() +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
