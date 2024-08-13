package com.osharif.spring_lms.Repos;

import com.osharif.spring_lms.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronsRepo extends JpaRepository<Patron, Long> {
}
