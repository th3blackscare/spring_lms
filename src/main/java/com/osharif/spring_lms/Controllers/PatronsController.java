package com.osharif.spring_lms.Controllers;

import com.osharif.spring_lms.DTO.patronDTO;
import com.osharif.spring_lms.Models.Patron;
import com.osharif.spring_lms.Repos.PatronsRepo;
import com.osharif.spring_lms.Services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronsController {

    @Autowired
    private PatronService patronService;

//    GET /api/patrons: Retrieve a list of all patrons
    @GetMapping("/")
    @ResponseBody
    public List<Patron> getAll(){
        return patronService.getAllPatronsActive();
    }

//    GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
    @GetMapping("/{id}")
    @ResponseBody
    public Patron getPatronById(@PathVariable("id") Long id){
        return patronService.getPatronById(id);
    }

//    POST /api/patrons: Add a new patron to the system.
    @PostMapping("/")
    @ResponseBody
    public Patron createPatron(@RequestBody patronDTO patron){
        return patronService.createPatron(patron);
    }

//    PUT /api/patrons/{id}: Update an existing patron's information.
    @PutMapping("/{id}")
    @ResponseBody
    public Patron updatePatron(@PathVariable("id") Long id, @RequestBody patronDTO patron){
        return patronService.update(id,patron);
    }

//    DELETE /api/patrons/{id}: Remove a patron from the system.
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deletePatron(@PathVariable("id") Long id){
        patronService.delete(id);
        com.osharif.spring_lms.Models.ResponseEntity responseEntity = new com.osharif.spring_lms.Models.ResponseEntity("to Be Removed", LocalDateTime.now(),200);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
