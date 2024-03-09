package com.example.Cinema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
