package com.example.Cinema_backend.repository;

import com.example.Cinema_backend.entity.Order;
import com.example.Cinema_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositry extends JpaRepository<Order,Long> {

    Order findOrderByPerson(Person person);

}
