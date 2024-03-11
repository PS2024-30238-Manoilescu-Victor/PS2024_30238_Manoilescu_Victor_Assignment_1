package com.example.Cinema_backend.controller;

import java.util.List;

import com.example.Cinema_backend.dto.TicketDTO;
import com.example.Cinema_backend.service.TicketService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/ticket")
public class TicketController {


    public static final Logger log = LoggerFactory.getLogger(TicketController.class.getName());

    //@Autowired
    TicketService ticketService;


    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Functie care selecteaza toate tichetele
     * @return O lista de comenzi
     */
    @GetMapping("/selectAll")
    public ResponseEntity<List<TicketDTO>> getTickets() {
        List<TicketDTO> dtos = ticketService.findTickets();
        int nr = dtos.size();
        log.info(nr + " Ticket(s) were found.");
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * cauta un tichet in functie de id-ul acestuia
     * @param id id-ul tichetului cautate
     * @return tichetul cu id-ul selectat
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        try {
            TicketDTO dtos = ticketService.findTicketById(id);
            log.info("Ticket with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.info("Ticket with id \"" + id + "\" was not found! "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Insereaza un nou tichet
     * @param ticketDTO tichet ce va fi inserat
     * @return id-ul tichetului inserat
     */
    @PostMapping()
    public ResponseEntity<Long> insertTicket(@Validated @RequestBody TicketDTO ticketDTO) {
        try {
            Long ticketID = ticketService.insert(ticketDTO);
            log.info("Ticket with id \"" + ticketID + "\" was inserted!");
            return new ResponseEntity<>(ticketID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Ticket was not inserted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    /**
     * Actualizeaza un tichet cu un id dat cu noi valori
     * @param ticketDTO noile valori puse in tichet
     * @param id id-ul tichetul ce va fi actualizat
     * @return id-ul tichetului actualizat
     */
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTicket(@Validated @RequestBody TicketDTO ticketDTO, @PathVariable Long id)
    {
        try {
            Long ticketID = ticketService.update(id, ticketDTO);
            log.info("Ticket with id \"" + ticketID + "\" was updated!");
            return new ResponseEntity<>(ticketID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Ticket with id \"" + id + "\" was not updated! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Sterge un tichet cu id dat
     * @param id id-ul tichetului ce va fi sters
     * @return id-ul tichetului sters
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTicket(@PathVariable Long id)
    {
        try {
            Long ticketID = ticketService.delete(id);
            log.info("Ticket with id \"" + ticketID + "\" was deleted!");
            return new ResponseEntity<>(ticketID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Ticket with id \"" + id + "\" was not deleted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

}
