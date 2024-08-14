package com.osharif.spring_lms.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class bookDTO {
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDate publishedDate;
    private Boolean isBorrowed;
    
    public bookDTO(String title, String author, String publisher, String isbn, String publishedDate, Boolean isBorrowed) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.setPublishedDate(publishedDate);
        this.isBorrowed = isBorrowed;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishedDate(String publishedDate) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.publishedDate = LocalDate.parse(publishedDate, dtf);
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public Boolean isBorrowed() {
        return isBorrowed;
    }
}
