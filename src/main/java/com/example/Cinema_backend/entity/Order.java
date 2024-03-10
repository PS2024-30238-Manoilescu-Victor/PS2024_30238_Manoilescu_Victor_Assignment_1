package com.example.Cinema_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter //
@Setter //
@Builder
@AllArgsConstructor //
@NoArgsConstructor //
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany
    @JoinTable(
            name = "ordered_Tickets",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private Set<Ticket> tickets;



}
