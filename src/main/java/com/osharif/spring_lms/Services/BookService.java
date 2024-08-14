package com.osharif.spring_lms.Services;

import com.osharif.spring_lms.DTO.bookDTO;
import com.osharif.spring_lms.Exceptions.NotFoundException;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Repos.BooksRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BooksRepo booksRepo;

    public List<Book> getAllBooks() {
        return booksRepo.findAll();
    }


    public List<Book> getAllBooksActive() {
        return booksRepo.findActive();
    }

    public Book getBookById(long bookId) {
        return booksRepo.findById(bookId).orElseThrow(()->new NotFoundException("Book not found"));
    }

    @Transactional
    public Book addBook(bookDTO book) {
        Book toBeSaved = new Book();
        if(book.getAuthor()!=null) toBeSaved.setAuthor(book.getAuthor());
        if(book.getTitle()!=null) toBeSaved.setTitle(book.getTitle());
        if(book.getIsbn()!=null) toBeSaved.setIsbn(book.getIsbn());
        if(book.getPublisher()!=null) toBeSaved.setPublisher(book.getPublisher());
        if(book.getPublishedDate() != null)toBeSaved.setPublishedDate(book.getPublishedDate());
        System.out.println("here");

        return booksRepo.save(toBeSaved);
    }

    @Transactional
    public Book updateBook(long bookId, bookDTO book) {
        Book toBeUpdated = getBookById(bookId);
        toBeUpdated.setId(bookId);
        if(book.getAuthor()!=null) toBeUpdated.setAuthor(book.getAuthor());
        if(book.getTitle()!=null) toBeUpdated.setTitle(book.getTitle());
        if(book.getIsbn()!=null) toBeUpdated.setIsbn(book.getIsbn());
        if(book.getPublisher()!=null) toBeUpdated.setPublisher(book.getPublisher());
        if(book.getPublishedDate() != null)toBeUpdated.setPublishedDate(book.getPublishedDate());
        if(book.isBorrowed() != null)toBeUpdated.setBorrowed(book.isBorrowed());
        return booksRepo.save(toBeUpdated);
    }

    @Transactional
    public Book removeBook(long bookId){
        Book toBeRemoved = getBookById(bookId);
        toBeRemoved.setAvailable(false);
        booksRepo.save(toBeRemoved);
        return toBeRemoved;
    }

}
