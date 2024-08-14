package com.osharif.spring_lms.Repos;

import com.osharif.spring_lms.Models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    @Query("SELECT t FROM Borrow t WHERE t.patronId = :patronId AND t.bookId = :bookId ")
    Optional<Borrow> findBorrowByPatronIdAndBookId(Long patronId, Long bookId);
}
