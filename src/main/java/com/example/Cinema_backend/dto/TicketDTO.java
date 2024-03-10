package com.example.Cinema_backend.dto;

import com.example.Cinema_backend.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private List<Orders> orders;


}
