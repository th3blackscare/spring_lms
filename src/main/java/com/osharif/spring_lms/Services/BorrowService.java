package com.osharif.spring_lms.Services;

import com.osharif.spring_lms.Exceptions.CantProcessException;
import com.osharif.spring_lms.Exceptions.NotFoundException;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Models.Borrow;
import com.osharif.spring_lms.Repos.BooksRepo;
import com.osharif.spring_lms.Repos.BorrowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private BookService bookService;

    public Borrow getById(long id) {
        return borrowRepository.findById(id).orElseThrow(()-> new NotFoundException("Borrow not found"));
    }

    public Borrow getByUserAndBook(long patronId, long bookId) {
//        borrowRepository.findBy()
        return borrowRepository.findBorrowByPatronIdAndBookId(patronId,bookId).orElseThrow(()-> new NotFoundException("Borrow not found"));
    }

    @Transactional
    public Borrow borrow(Long userId,Long bookId, Long patronId){
        Borrow borrow = new Borrow();
        borrow.setPatronId(patronId);
        borrow.setBookId(bookId);
        borrow.setUserId(userId);
        Book book = bookService.getBookById(bookId);
        if(book.isBorrowed()) throw new CantProcessException("book is already borrowed");
        book.setBorrowed(true);
        booksRepo.save(book);
        return borrowRepository.save(borrow);
    }

    @Transactional
    public Borrow returnBorrow(Long bookId, Long patronId){
        Borrow borrow = getByUserAndBook(patronId,bookId);
        borrow.setReturnDate(LocalDate.now());
        Book book = bookService.getBookById(bookId);
        book.setBorrowed(false);
        booksRepo.save(book);
        return borrowRepository.save(borrow);
    }

}
