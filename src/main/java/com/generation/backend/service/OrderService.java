package com.generation.backend.service;

import com.generation.backend.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order createOrder (Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    Order getOrderByName(String name);

    Order updateOrder(Order order);

    Map<String, String> deleteOrderById(Long id);
}
