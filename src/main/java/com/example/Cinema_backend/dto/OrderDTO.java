package com.example.Cinema_backend.dto;

import com.example.Cinema_backend.entity.Person;
import com.example.Cinema_backend.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter //
@Setter //
@AllArgsConstructor //
@NoArgsConstructor //
public class OrderDTO {

    private Long id;
    private Person person;
    private Set<Ticket> tickets;

}
