package com.osharif.spring_lms.Security.Service;

import com.osharif.spring_lms.Security.Models.Token;
import com.osharif.spring_lms.Security.Repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token getToken(String id) {
        return tokenRepository.findByIdAndRevoked(id,false);
    }

    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
