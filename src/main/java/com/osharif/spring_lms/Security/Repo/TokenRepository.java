package com.osharif.spring_lms.Security.Repo;

import com.osharif.spring_lms.Security.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, String> {
    @Query("SELECT t FROM Token t WHERE t.id = :id AND t.revoked = :revoked")
    Token findByIdAndRevoked(@Param("id") String id, @Param("revoked") boolean revoked);
}
