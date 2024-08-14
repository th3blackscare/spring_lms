package com.osharif.spring_lms.Controllers;

import com.osharif.spring_lms.Models.Borrow;
import com.osharif.spring_lms.Security.Models.User;
import com.osharif.spring_lms.Services.BorrowService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

//    ● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
//    borrow a book.
//● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @ResponseBody
    public Borrow borrowBook(@PathVariable("bookId") Long bookId, @PathVariable Long patronId, Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        return borrowService.borrow(userDetails.getId(),bookId,patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @ResponseBody
    public Borrow returnBook(@PathVariable("bookId") Long bookId, @PathVariable Long patronId){
        return borrowService.returnBorrow(bookId,patronId);
    }
}
