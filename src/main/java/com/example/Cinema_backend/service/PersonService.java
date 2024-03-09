package com.example.Cinema_backend.service;

import com.example.Cinema_backend.dto.PersonDTO;
import com.example.Cinema_backend.entity.Person;
import com.example.Cinema_backend.mapper.PersonMapper;
import com.example.Cinema_backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PersonService {


    //public static final Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonMapper::fromPerson)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(Long id) throws Exception {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            //log.error("Person with id {} was not found in db", id);
            throw new Exception(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonMapper.fromPerson(personOptional.get());
    }


    public Long insert(PersonDTO personDTO) {
        Person person = PersonMapper.toPerson(personDTO);
        person = personRepository.save(person);
        //LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public Long update(Long id, PersonDTO personDTO) throws Exception {
        //Person person = PersonMapper.toPerson(personDTO);
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = PersonMapper.toPerson(personDTO);//personOptional.get();
            person.setId(id);
            personRepository.save(person);
            return id;
        }
        else {
            throw new Exception("The person with id \"" + id + "\" doesn't exist!");
        }
    }

    public Long delete(Long id) throws Exception {
        //Person person = PersonMapper.toPerson(personDTO);
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            personRepository.deleteById(id);
            return id;
        }
        else {
            throw new Exception("The person with id \"" + id + "\" doesn't exist!");
        }
    }





}
