package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findOrderById(Long id);

    List<Order> findAllOrders();

    void createOrder(Long productId, Long userId);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrderById(Long id);
}