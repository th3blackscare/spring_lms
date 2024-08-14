package com.osharif.spring_lms;

import com.osharif.spring_lms.DTO.bookDTO;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Models.Borrow;
import com.osharif.spring_lms.Services.BookService;
import com.osharif.spring_lms.Services.BorrowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BorrowServiceTests {

    long lastAdded;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BookService bookService;

    @Test
    public void testBorrow() {
        Borrow borrow = new Borrow(9,1,4);
        Borrow result = borrowService.borrow(1L,9L,4L);
        assertEquals(result,borrow);
    }

    @Test
    public void testReturnBook() {
        Borrow borrow = borrowService.getByUserAndBook(4,9);
        Book book1 = bookService.getBookById(9);
        book1.setBorrowed(false);
        borrow.setReturnDate(LocalDate.now());
        Borrow result = borrowService.returnBorrow(9L,4L);
        Book book2 = bookService.getBookById(9);
        assertEquals(borrow,result);
        assertEquals(book1,book2);
    }

}
