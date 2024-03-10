package com.example.Cinema_backend.dto;

import com.example.Cinema_backend.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter //
@Setter //
@AllArgsConstructor //
@NoArgsConstructor //
public class TicketDTO {

    private Long id;

    private String nume;
    private Integer rating;
    private Float pret;
    private String data;
    private String ora;
    private Integer nrTickets;
    private Set<Order> orders;


}
