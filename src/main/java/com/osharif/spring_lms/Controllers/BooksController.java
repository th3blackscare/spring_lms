package com.osharif.spring_lms.Controllers;

import com.osharif.spring_lms.DTO.bookDTO;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookService bookService;

//    POST /api/books: Add a new book to the library.
    @PostMapping("/")
    @ResponseBody
    public Book addBook(bookDTO book) {
        return bookService.addBook(book);
    }

//     GET /api/books: Retrieve a list of all books.
    @GetMapping("/")
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookService.getAllBooksActive();
    }

//    GET /api/books/{id}: Retrieve details of a specific book by ID.
    @GetMapping("/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

//    PUT /api/books/{id}: Update an existing book's information.
    @PutMapping("/{id}")
    @ResponseBody
    public Book updateBook(@PathVariable("id") Long id, bookDTO book) {
        return bookService.updateBook(id, book);
    }

//    DELETE /api/books/{id}: Remove a book from the library.
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteBook(@PathVariable("id") Long id) {
        bookService.removeBook(id);
        com.osharif.spring_lms.Models.ResponseEntity responseEntity = new com.osharif.spring_lms.Models.ResponseEntity("to Be Remove", LocalDateTime.now(),200);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

}
