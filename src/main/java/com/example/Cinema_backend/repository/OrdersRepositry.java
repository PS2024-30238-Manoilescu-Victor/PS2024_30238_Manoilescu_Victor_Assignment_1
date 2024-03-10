package com.example.Cinema_backend.repository;

import com.example.Cinema_backend.entity.Orders;
import com.example.Cinema_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepositry extends JpaRepository<Orders,Long> {

    Orders findOrderByPerson(Person person);

}
