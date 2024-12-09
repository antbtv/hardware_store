package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Order;
import com.example.hardware_store.repository.OrderRepository;
import com.example.hardware_store.service.entity.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void createOrder(Long productId, Long userId) {
        String sql = "CALL create_order(?, ?)";
        jdbcTemplate.update(sql, productId, userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrderStatus(Order order, String status){
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
