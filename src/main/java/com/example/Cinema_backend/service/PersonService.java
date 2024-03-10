package com.example.Cinema_backend.service;

import com.example.Cinema_backend.dto.OrdersDTO;
import com.example.Cinema_backend.dto.PersonDTO;
import com.example.Cinema_backend.entity.Orders;
import com.example.Cinema_backend.entity.Person;
import com.example.Cinema_backend.entity.Ticket;
import com.example.Cinema_backend.mapper.PersonMapper;
import com.example.Cinema_backend.repository.PersonRepository;
import com.example.Cinema_backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class PersonService {


    //public static final Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    OrdersService ordersService;

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


    public Long createOrder(Long idPerson,Long idTicket) throws Exception {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(currentDate);
        Optional<Person> personOptional = personRepository.findById(idPerson);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            Optional<Ticket> ticketOptional = ticketRepository.findById(idTicket);
            if (ticketOptional.isPresent()) {
                //Ticket ticket = ticketOptional.get();
                OrdersDTO aux2 = new OrdersDTO();
                aux2.setDataComanda(currentDateTime);
                aux2.setPerson(person);
                Long orderId = ordersService.insert2(aux2,idTicket);
                return orderId;
            }
            else {
                throw new Exception("The ticket with id \"" + idTicket + "\" doesn't exist!");
            }

        } else {
            throw new Exception("The person with id \"" + idPerson + "\" doesn't exist!");
        }

    }

    /*public Long addOrder2(Long personId,List<Long> ticketIds) throws Exception {
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(currentDate);
            Optional<Person> personOptional = personRepository.findById(personId);
            if (personOptional.isPresent()) {
                Person person = personOptional.get();
                List<Orders> orders = person.getOrders();
                for (Long id : ticketIds) {

                    Orders aux = new Orders();

                }
                return personId;
            } else {
                throw new Exception("The person with id \"" + personId + "\" doesn't exist!");
            }

        }*/


}
