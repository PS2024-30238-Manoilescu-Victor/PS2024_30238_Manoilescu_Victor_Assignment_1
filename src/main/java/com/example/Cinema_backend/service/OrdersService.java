package com.example.Cinema_backend.service;


import com.example.Cinema_backend.dto.OrdersDTO;
import com.example.Cinema_backend.entity.Orders;
import com.example.Cinema_backend.entity.Ticket;
import com.example.Cinema_backend.mapper.OrdersMapper;
import com.example.Cinema_backend.repository.OrdersRepositry;
import com.example.Cinema_backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    OrdersRepositry ordersRepositry;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    public OrdersService(OrdersRepositry ordersRepositry) {
        this.ordersRepositry = ordersRepositry;
    }

    public List<OrdersDTO> findOrders(){
        List<Orders> ordersList = ordersRepositry.findAll();
        return ordersList.stream()
                .map(OrdersMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrdersDTO findOrderById(Long id) throws Exception
    {
        Optional<Orders> orderOptional = ordersRepositry.findById(id);
        if (!orderOptional.isPresent()) {
            //log.error("Order with id {} was not found in db", id);
            throw new Exception(Orders.class.getSimpleName() + " with id: " + id + "was not found.");
        }
        return OrdersMapper.fromOrder(orderOptional.get());
    }

    public Long insert(OrdersDTO ordersDTO)
    {
        Orders orders = OrdersMapper.toOrder(ordersDTO);
        orders = ordersRepositry.save(orders);
        return orders.getId();
    }

    public Long insert2(OrdersDTO ordersDTO,Long idTicket) throws Exception
    {
        Orders orders = OrdersMapper.toOrder(ordersDTO);
        Optional<Ticket> ticketOptional = ticketRepository.findById(idTicket);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            List<Ticket> aux = orders.getTickets();
            if(aux == null)
            {
                aux = new ArrayList<Ticket>();
            }
            aux.add(ticket);
            orders.setTickets(aux);
            orders = ordersRepositry.save(orders);
            return orders.getId();
        }
        else
        {
            throw new Exception("The ticket with id \"" + idTicket + "\" doesn't exist!");
        }
    }

    public Long update(Long id, OrdersDTO ordersDTO) throws Exception
    {
        Optional<Orders> ordersOptional = ordersRepositry.findById(id);
        if (ordersOptional.isPresent()) {
            Orders orders = OrdersMapper.toOrder(ordersDTO);//personOptional.get();
            orders.setId(id);
            ordersRepositry.save(orders);
            return id;
        }
        else {
            throw new Exception("The order with id \"" + id + "\" doesn't exist!");
        }
    }

    public Long delete(Long id) throws Exception {
        Optional<Orders> orderOptional = ordersRepositry.findById(id);
        if (orderOptional.isPresent()) {
            ordersRepositry.deleteById(id);
            return id;
        }
        else {
            throw new Exception("The order with id \"" + id + "\" doesn't exist!");
        }
    }

    public Long addTicket(Long idOrder, Long idTicket) throws Exception
    {
        Optional<Orders> ordersOptional = ordersRepositry.findById(idOrder);
        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();
            Optional<Ticket> ticketOptional = ticketRepository.findById(idTicket);
            if (ticketOptional.isPresent()) {
                Ticket ticket = ticketOptional.get();
                List<Ticket> aux = orders.getTickets();
                aux.add(ticket);
                orders.setTickets(aux);
                ordersRepositry.save(orders);
                return idOrder;
            }
            else
            {
                throw new Exception("The ticket with id \"" + idTicket + "\" doesn't exist!");
            }

        }
        else {
            throw new Exception("The order with id \"" + idOrder + "\" doesn't exist!");

        }
        //return idOrder;
    }

}
