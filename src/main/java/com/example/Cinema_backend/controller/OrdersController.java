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

    @GetMapping("/selectAll")
    public ResponseEntity<List<OrdersDTO>> getOrders() {
        List<OrdersDTO> dtos = ordersService.findOrders();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id) {
        try {
            OrdersDTO dtos = ordersService.findOrderById(id);
            log.info("Order with id \"" + id + "\" was found!");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Long> insertOrder(@Validated @RequestBody OrdersDTO ordersDTO) {
        try {
            Long orderID = ordersService.insert(ordersDTO);
            return new ResponseEntity<>(orderID, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

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
