package com.osharif.spring_lms.Repos;

import com.osharif.spring_lms.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatronsRepo extends JpaRepository<Patron, Long> {

    @Query("SELECT t FROM Patron t WHERE t.isActive = :isActive")
    List<Patron> findAllByStatus(boolean isActive);
}
