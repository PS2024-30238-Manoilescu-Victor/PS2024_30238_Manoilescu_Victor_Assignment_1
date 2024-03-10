package com.example.Cinema_backend.mapper;

import com.example.Cinema_backend.dto.TicketDTO;
import com.example.Cinema_backend.entity.Ticket;

public class TicketMapper {

    public static Ticket toTicket(TicketDTO ticketDTO)
    {
        Ticket aux = new Ticket();
        aux.setId(ticketDTO.getId());
        aux.setNume(ticketDTO.getNume());
        aux.setRating(ticketDTO.getRating());
        aux.setPret(ticketDTO.getPret());
        aux.setData(ticketDTO.getData());
        aux.setOra(ticketDTO.getOra());
        aux.setNrTickets(ticketDTO.getNrTickets());
        aux.setOrders(ticketDTO.getOrders());
        return aux;
    }

    public static TicketDTO fromTicket(Ticket ticket)
    {
        TicketDTO aux = new TicketDTO();
        aux.setId(ticket.getId());
        aux.setNume(ticket.getNume());
        aux.setRating(ticket.getRating());
        aux.setPret(ticket.getPret());
        aux.setData(ticket.getData());
        aux.setOra(ticket.getOra());
        aux.setNrTickets(ticket.getNrTickets());
        aux.setOrders(ticket.getOrders());
        return aux;
    }

}
