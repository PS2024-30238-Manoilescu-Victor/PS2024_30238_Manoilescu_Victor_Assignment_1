package com.example.Cinema_backend.service;


import com.example.Cinema_backend.dto.OrdersDTO;
import com.example.Cinema_backend.entity.Orders;
import com.example.Cinema_backend.mapper.OrdersMapper;
import com.example.Cinema_backend.repository.OrdersRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    OrdersRepositry ordersRepositry;

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

}
