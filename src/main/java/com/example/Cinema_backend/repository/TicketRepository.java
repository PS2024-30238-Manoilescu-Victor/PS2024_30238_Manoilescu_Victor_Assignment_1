package com.example.Cinema_backend.repository;

import com.example.Cinema_backend.entity.Person;
import com.example.Cinema_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Ticket findTicketByNume(String nume);

}
