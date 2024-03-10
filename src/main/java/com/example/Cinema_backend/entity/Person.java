package com.example.Cinema_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Getter //
@Setter //
@Builder
@AllArgsConstructor //
@NoArgsConstructor //
@Table(name = "person")

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nume;
    @Column
    private String prenume;
    @Column
    private String email;
    @Column
    //@OneToOne pt relatie one to one;
    //@OneToMany are lista/set; mapam cu numele tabelei
    //@ManyToOne ;mapam cu un user
    //@ManyToMany are set; fol cascade(facem o tabela de mijloc din care luam)
    private String nrTelefon;
    @Column
    private Boolean isAdmin;
    @OneToMany
    private Set<Order> orders;

}
