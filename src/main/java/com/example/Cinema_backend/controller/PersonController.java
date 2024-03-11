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

    /**
     * Functie care selecteaza toate persoanele
     * @return O lista de comenzi
     */
    @GetMapping("/selectAll")
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        int nr = dtos.size();
        if(nr == 1)
            log.info(nr + " Person was found.");
        else
            log.info(nr + " People were found.");
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * cauta o persoana in functie de id-ul acesteia
     * @param id id-ul persoanei cautate
     * @return persoana cu id-ul selectat
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        try {
        PersonDTO dtos = personService.findPersonById(id);
            log.info("User with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.info("User with id \"" + id + "\" was not found! "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Insereaza o noua persoana
     * @param personDTO persoana ce va fi inserata
     * @return id-ul noii persoane inserate
     */
    @PostMapping()
    public ResponseEntity<Long> insertPerson(@Validated @RequestBody PersonDTO personDTO) {
        try {
            Long personID = personService.insert(personDTO);
            log.info("Person with id \"" + personID + "\" was inserted!");
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Person was not inserted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    /**
     * Actualizeaza o persoana cu un id dat cu noi valori
     * @param personDTO noile valori puse pentru persoana
     * @param id id-ul persoanei ce va fi actualizata
     * @return id-ul persoanei actualizate
     */
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

    /**
     * Plaseaza o noua comanda ce contine un tichet
     * @param idPerson persoana care v-a detine comanda
     * @param idTicket id-ul tichetului continut de comanda
     * @return id-ul persoanei ce a plasat noua comanda
     */
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


    /**
     * Sterge o persoana cu id dat
     * @param id id-ul persoanei ce va fi sterse
     * @return id-ul persoanei sterse
     */
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

