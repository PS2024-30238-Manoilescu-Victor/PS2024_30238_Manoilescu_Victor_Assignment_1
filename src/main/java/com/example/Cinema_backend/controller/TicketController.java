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

    @GetMapping("/selectAll")
    public ResponseEntity<List<TicketDTO>> getTickets() {
        List<TicketDTO> dtos = ticketService.findTickets();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        try {
            TicketDTO dtos = ticketService.findTicketById(id);
            log.info("Ticket with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Long> insertTicket(@Validated @RequestBody TicketDTO ticketDTO) {
        try {
            Long ticketID = ticketService.insert(ticketDTO);
            return new ResponseEntity<>(ticketID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

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
