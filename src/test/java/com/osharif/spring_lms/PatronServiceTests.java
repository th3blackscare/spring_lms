package com.osharif.spring_lms;

import com.osharif.spring_lms.DTO.bookDTO;
import com.osharif.spring_lms.DTO.patronDTO;
import com.osharif.spring_lms.Models.Book;
import com.osharif.spring_lms.Models.Patron;
import com.osharif.spring_lms.Services.BookService;
import com.osharif.spring_lms.Services.PatronService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatronServiceTests {

    long lastAdded;
    @Autowired
    private PatronService patronService;
    @Test
    public void testAddPatron() {
        patronDTO patronDTO = new patronDTO("Omar","Sharif","fayoum","01100060682","osharif@codelyticaleg.com","2000-04-03",true);
        Patron expected = new Patron(patronDTO.getFirstName(),patronDTO.getLastName(),patronDTO.getAddress(),patronDTO.getPhone(), patronDTO.getEmail(), patronDTO.getBirthDate());
        Patron result = patronService.createPatron(patronDTO);
        lastAdded = result.getId();
        assertEquals(expected, result);
    }

    @Test
    public void testUpdatePatron() {
        patronDTO patronDTO = new patronDTO("Omar","Sharif","fayoum","01000455834","osharif@codelyticaleg.com","2000-04-03",true);
        Patron expected = new Patron(patronDTO.getFirstName(),patronDTO.getLastName(),patronDTO.getAddress(),patronDTO.getPhone(), patronDTO.getEmail(), patronDTO.getBirthDate());
        Patron result = patronService.update(4L,patronDTO);
        lastAdded = result.getId();
        assertEquals(expected, result);
    }

    @Test
    public void testDeletePatron() {
        Patron patron = patronService.getPatronById(4L);
        patron.setActive(false);
        Patron result = patronService.delete(patron.getId());
        assertEquals(patron, result);
    }
}
