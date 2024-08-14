package com.osharif.spring_lms.Repos;

import com.osharif.spring_lms.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepo extends JpaRepository<Book,Long>{
    @Query("SELECT t FROM Book t WHERE t.available = true")
    List<Book> findActive();
}
