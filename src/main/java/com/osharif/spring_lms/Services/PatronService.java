package com.osharif.spring_lms.Services;

import com.osharif.spring_lms.Repos.PatronsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatronService {
    @Autowired
    private PatronsRepo patronsRepo;
}
