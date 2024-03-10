package com.example.Cinema_backend.repository;

import com.example.Cinema_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{ //CrudRepository<Person,Long> {

    Person findPersonByNumeAndPrenume(String nume, String prenume);
    List<Person> findByNume(String nume);

    //Person getPersonByNumeAndPrenume(String nume, String prenume);

}
