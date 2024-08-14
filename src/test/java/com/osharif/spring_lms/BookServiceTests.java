package com.osharif.spring_lms;

import com.osharif.spring_lms.DTO.bookDTO;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Repos.BooksRepo;
import com.osharif.spring_lms.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    long lastAdded;
    @Autowired
    private BookService bookService;
    @Test
    public void testAddBook() {
        bookDTO bookDTO = new bookDTO("Test1","test author","testt","111222-555-222", "2024-01-01",true);
        Book expected = new Book(bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getPublisher(),bookDTO.getIsbn(), bookDTO.getPublishedDate());
        Book result = bookService.addBook(bookDTO);
        lastAdded = result.getId();
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateBook() {
        bookDTO bookDTO = new bookDTO("Test22","test author","testt","22221-555-222", "2024-01-01",false);
        Book expected = new Book(bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getPublisher(),bookDTO.getIsbn(), bookDTO.getPublishedDate());
        Book result = bookService.updateBook(9,bookDTO);
        assertEquals(expected, result);
    }

    @Test
    public void testRemoveBook(){
        Book expected = bookService.getBookById(9);
        expected.setAvailable(false);
        Book result = bookService.removeBook(9);
        assertEquals(expected, result);
    }
}
