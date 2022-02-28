package com.example.service;



import com.example.dao.entity.Item;
import com.example.dao.entity.Order;
import com.example.dao.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    void deleteById(Long id);

    void deleteByDateOfPurchases(LocalDateTime dateOfPurchases);

    Order update(Order order);

    List<Order> findAllList();

    Order findById(Long id);

    void saveToOrder(Long productId);

    Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Order createOrder(List<Item> basket, User user);

}