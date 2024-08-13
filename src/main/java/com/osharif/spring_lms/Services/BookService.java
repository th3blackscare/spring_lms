package com.osharif.spring_lms.Services;

import com.osharif.spring_lms.Repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BooksRepo booksRepo;
}
