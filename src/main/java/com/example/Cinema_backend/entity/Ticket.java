package com.example.Cinema_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.List;

@Entity
@Getter //
@Setter //
@Builder
@AllArgsConstructor //
@NoArgsConstructor //
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nume;
    @Column
    private Integer rating;
    @Column
    private Float pret;
    @Column
    private String data;
    @Column
    private String ora;
    @Column
    private Integer nrTickets;
    @ManyToMany(mappedBy = "tickets", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Orders> orders;


}
