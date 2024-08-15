package com.osharif.spring_lms.Repos;

import com.osharif.spring_lms.Models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    @Query("SELECT t FROM Borrow t WHERE t.patron.id = :patronId AND t.book.id = :bookId ")
    Optional<Borrow> findBorrowByPatronIdAndBookId(Long patronId, Long bookId);
}
