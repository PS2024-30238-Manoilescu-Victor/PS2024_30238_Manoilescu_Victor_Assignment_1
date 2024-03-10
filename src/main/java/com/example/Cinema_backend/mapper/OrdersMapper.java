package com.example.Cinema_backend.mapper;

import com.example.Cinema_backend.dto.OrdersDTO;
import com.example.Cinema_backend.entity.Orders;

public class OrdersMapper {

    public static Orders toOrder(OrdersDTO ordersDTO)
    {
        Orders aux = new Orders();
        aux.setId(ordersDTO.getId());
        aux.setDataComanda(ordersDTO.getDataComanda());
        aux.setPerson(ordersDTO.getPerson());
        aux.setTickets(ordersDTO.getTickets());
        return aux;
    }

    public static OrdersDTO fromOrder(Orders orders)
    {
        OrdersDTO aux = new OrdersDTO();
        aux.setId(orders.getId());
        aux.setDataComanda(orders.getDataComanda());
        aux.setPerson(orders.getPerson());
        aux.setTickets(orders.getTickets());
        return aux;
    }

}
