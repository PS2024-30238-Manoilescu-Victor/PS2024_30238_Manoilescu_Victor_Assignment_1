package com.example.Cinema_backend.service;

import com.example.Cinema_backend.dto.TicketDTO;
import com.example.Cinema_backend.entity.Ticket;
import com.example.Cinema_backend.mapper.TicketMapper;
import com.example.Cinema_backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {this.ticketRepository = ticketRepository;}

    public List<TicketDTO> findTickets() {
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList.stream()
                .map(TicketMapper::fromTicket)
                .collect(Collectors.toList());
    }

    public TicketDTO findTicketById(Long id) throws Exception {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (!ticketOptional.isPresent()) {
            //log.error("Ticket with id {} was not found in db", id);
            throw new Exception(Ticket.class.getSimpleName() + " with id: " + id + "was not found.");
        }
        return TicketMapper.fromTicket(ticketOptional.get());
    }


    public Long insert(TicketDTO ticketDTO) {
        Ticket ticket = TicketMapper.toTicket(ticketDTO);
        ticket = ticketRepository.save(ticket);
        //LOGGER.debug("Ticket with id {} was inserted in db", ticket.getId());
        return ticket.getId();
    }

    public Long update(Long id, TicketDTO ticketDTO) throws Exception {
        //Ticket ticket = TicketMapper.toTicket(ticketDTO);
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            Ticket ticket = TicketMapper.toTicket(ticketDTO);//ticketOptional.get();
            ticket.setId(id);
            ticketRepository.save(ticket);
            return id;
        }
        else {
            throw new Exception("The ticket with id \"" + id + "\" doesn't exist!");
        }
    }

    public Long delete(Long id) throws Exception {
        //Ticket ticket = TicketMapper.toTicket(ticketDTO);
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            ticketRepository.deleteById(id);
            return id;
        }
        else {
            throw new Exception("The ticket with id \"" + id + "\" doesn't exist!");
        }
    }


}
