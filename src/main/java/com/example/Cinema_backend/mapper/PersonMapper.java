package com.example.Cinema_backend.mapper;

import com.example.Cinema_backend.dto.PersonDTO;
import com.example.Cinema_backend.entity.Person;

public class PersonMapper {

    public static Person toPerson(PersonDTO personDTO)
    {
        Person aux = new Person();
        aux.setId(personDTO.getId());
        aux.setEmail(personDTO.getEmail());
        aux.setNume(personDTO.getNume());
        aux.setPrenume(personDTO.getPrenume());
        aux.setNrTelefon(personDTO.getNrTelefon());
        aux.setIsAdmin(personDTO.getIsAdmin());
        return aux;
    }

    public static PersonDTO fromPerson(Person person)
    {
        PersonDTO aux = new PersonDTO();
        aux.setId(person.getId());
        aux.setEmail(person.getEmail());
        aux.setNume(person.getNume());
        aux.setPrenume(person.getPrenume());
        aux.setNrTelefon(person.getNrTelefon());
        aux.setIsAdmin(person.getIsAdmin());
        return aux;
    }

}

