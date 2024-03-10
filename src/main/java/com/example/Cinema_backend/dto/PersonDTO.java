package com.example.Cinema_backend.dto;

import com.example.Cinema_backend.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter //
    @Setter //
    @AllArgsConstructor //
    @NoArgsConstructor //
    public class PersonDTO {

        private Long id;
        private String nume;
        private String prenume;
        private String email;
        private String nrTelefon;
        private Boolean isAdmin;
        private Set<Order> orders;

}
