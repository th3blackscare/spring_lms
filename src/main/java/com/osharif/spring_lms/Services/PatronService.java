package com.osharif.spring_lms.Services;

import com.osharif.spring_lms.DTO.patronDTO;
import com.osharif.spring_lms.Exceptions.NotFoundException;
import com.osharif.spring_lms.Models.Patron;
import com.osharif.spring_lms.Repos.PatronsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    @Autowired
    private PatronsRepo patronsRepo;

    public List<Patron> getAllPatrons() {
        return patronsRepo.findAll();
    }

    public List<Patron> getAllPatronsActive() {
        return patronsRepo.findAllByStatus(true);
    }

    public Patron getPatronById(long id) {
        return patronsRepo.findById(id).orElseThrow(()->new NotFoundException("Patron Not Found"));
    }

    @Transactional
    public Patron createPatron(patronDTO patron) {
        Patron newPatron = new Patron();
        if (patron.getFirstName() != null) newPatron.setFirstName(patron.getFirstName());
        if (patron.getLastName() != null) newPatron.setLastName(patron.getLastName());
        if (patron.getAddress() != null) newPatron.setAddress(patron.getAddress());
        if (patron.getBirthDate() != null) newPatron.setBirthDate(patron.getBirthDate());
        if (patron.getEmail() != null) newPatron.setEmail(patron.getEmail());
        if (patron.getPhone() != null) newPatron.setPhone(patron.getPhone());
        return patronsRepo.save(newPatron);
    }

    @Transactional
    public Patron update(Long id,patronDTO patron) {
        Patron toBeUpdated = getPatronById(id);
        if (patron.getFirstName() != null) toBeUpdated.setFirstName(patron.getFirstName());
        if (patron.getLastName() != null) toBeUpdated.setLastName(patron.getLastName());
        if (patron.getAddress() != null) toBeUpdated.setAddress(patron.getAddress());
        if (patron.getBirthDate() != null) toBeUpdated.setBirthDate(patron.getBirthDate());
        if (patron.getEmail() != null) toBeUpdated.setEmail(patron.getEmail());
        if (patron.getPhone() != null) toBeUpdated.setPhone(patron.getPhone());
        return patronsRepo.save(toBeUpdated);
    }

    @Transactional
    public Patron delete(Long id){
        Patron patron = getPatronById(id);
        patron.setActive(false);
        patronsRepo.save(patron);
        return patron;
    }

}
