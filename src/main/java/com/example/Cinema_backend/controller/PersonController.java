package com.example.Cinema_backend.controller;

import com.example.Cinema_backend.dto.PersonDTO;
import com.example.Cinema_backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
//import

import java.util.List;
import org.slf4j.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
//@RequestMapping()
public class PersonController {

    public static final Logger log = LoggerFactory.getLogger(PersonController.class.getName());

    //@Autowired
    PersonService personService;


    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        try {
        PersonDTO dtos = personService.findPersonById(id);
            log.info("User with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Long> insertPerson(@Validated @RequestBody PersonDTO personDTO) {
        try {
            Long personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePerson(@Validated @RequestBody PersonDTO personDTO, @PathVariable Long id)
    {
        try {
            Long personID = personService.update(id, personDTO);
            log.info("User with id \"" + personID + "\" was updated!");
            return new ResponseEntity<>(personID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("User with id \"" + id + "\" was not updated! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("createOrder/{idPerson}/{idTicket}")
    public ResponseEntity<Long> createOrder(@PathVariable Long idPerson, @PathVariable Long idTicket)
    {
        try {
            Long orderID = personService.createOrder(idPerson, idTicket);
            log.info("User with id \"" + idPerson + "\" created the order with id " + orderID);
            return new ResponseEntity<>(idPerson, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("User with id \"" + idPerson + "\" didn't create an order " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable Long id)
    {
        try {
            Long personID = personService.delete(id);
            log.info("User with id \"" + personID + "\" was deleted!");
            return new ResponseEntity<>(personID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("User with id \"" + id + "\" was not deleted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    /*
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    } */




}

