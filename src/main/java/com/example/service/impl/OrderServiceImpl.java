package com.example.service.impl;


import com.example.dao.entity.Orders;
import com.example.dao.repository.OrderRepository;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Orders save(Orders order) {
        validate(order.getId() != 0, "error.order.notHaveId");
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByDateOfPurchases(LocalDateTime dateOfPurchases) {
        validate(!orderRepository.deleteByDateOfPurchase(dateOfPurchases), "error.order.dateOfPurchases.notUnique");
        orderRepository.deleteByDateOfPurchase(dateOfPurchases);

    }

    @Override
    public Orders updateOrder(Orders order) {
        final Long id = order.getId();
        validate(id == null, "error.order.haveId");
        findById(id);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders findById(Long id) {

        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

}

