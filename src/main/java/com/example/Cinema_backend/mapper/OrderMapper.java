package com.example.Cinema_backend.mapper;

import com.example.Cinema_backend.dto.OrderDTO;
import com.example.Cinema_backend.entity.Order;

public class OrderMapper {

    public static Order toOrder(OrderDTO orderDTO)
    {
        Order aux = new Order();
        aux.setId(orderDTO.getId());
        aux.setPerson(orderDTO.getPerson());
        aux.setTickets(orderDTO.getTickets());
        return aux;
    }

    public static OrderDTO fromOrder(Order order)
    {
        OrderDTO aux = new OrderDTO();
        aux.setId(order.getId());
        aux.setPerson(order.getPerson());
        aux.setTickets(order.getTickets());
        return aux;
    }

}
