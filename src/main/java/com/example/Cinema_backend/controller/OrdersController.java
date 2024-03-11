package com.example.Cinema_backend.controller;

import java.util.List;

import com.example.Cinema_backend.dto.OrdersDTO;
import com.example.Cinema_backend.service.OrdersService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {

    public static final Logger log = LoggerFactory.getLogger(PersonController.class.getName());

    //@Autowired
    OrdersService ordersService;


    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Functie care selecteaza toate comenzile
     * @return O lista de comenzi
     */
    @GetMapping("/selectAll")
    public ResponseEntity<List<OrdersDTO>> getOrders() {
        List<OrdersDTO> dtos = ordersService.findOrders();
        int nr = dtos.size();
        log.info(nr + " Order(s) were found.");
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * cauta o comanda in functie de id-ul acesteia
     * @param id id-ul comenzii cautate
     * @return comanda cu id-ul selectat
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id) {
        try {
            OrdersDTO dtos = ordersService.findOrderById(id);
            log.info("Order with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.info("Order with id \"" + id + "\" was not found! "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Insereaza o noua comanda
     * @param ordersDTO comanda ce va fi inserata
     * @return id-ul noii comenzi inserate
     */
    @PostMapping()
    public ResponseEntity<Long> insertOrder(@Validated @RequestBody OrdersDTO ordersDTO) {
        try {
            Long orderID = ordersService.insert(ordersDTO);
            log.info("Order with id \"" + orderID + "\" was inserted!");
            return new ResponseEntity<>(orderID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Order was not inserted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Actualizeaza o comanda cu un id dat cu noi valori
     * @param ordersDTO noile valori puse in comanda
     * @param id id-ul comenzii ce va fi actualizata
     * @return id-ul comenzii actualizate
     */
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateOrder(@Validated @RequestBody OrdersDTO ordersDTO, @PathVariable Long id)
    {
        try {
            Long orderID = ordersService.update(id, ordersDTO);
            log.info("Order with id \"" + orderID + "\" was updated!");
            return new ResponseEntity<>(orderID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Order with id \"" + id + "\" was not updated! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Adauga un bilet la comanda cu id-ul dat
     * @param idOrder id-ul comenzii in care se va adauga tichetul
     * @param idTicket id-ul biletului ce va fi adaugat la comanda
     * @return id-ul comenzii
     */
    @PutMapping("addTicket/{idOrder}/{idTicket}")
    public ResponseEntity<Long> addTicketToOrder(@PathVariable Long idOrder, @PathVariable Long idTicket)
    {
        try {
            Long orderID = ordersService.addTicket(idOrder, idTicket);
            log.info("Ticket with id \"" + idTicket + "\" was added to order " + idOrder + ".");
            return new ResponseEntity<>(orderID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Ticket with id \"" + idTicket + "\" was not added to order " + idOrder + ". " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Sterge o comanda cu id dat
     * @param id id-ul comenzii ce va fi sterse
     * @return id-ul comenzii sterse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id)
    {
        try {
            Long orderID = ordersService.delete(id);
            log.info("Order with id \"" + orderID + "\" was deleted!");
            return new ResponseEntity<>(orderID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.info("Order with id \"" + id + "\" was not deleted! " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

}
