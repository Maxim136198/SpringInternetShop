package com.example.service;



import com.example.dao.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Orders save(Orders order);

    void deleteById(Long id);

    void deleteByDateOfPurchases(LocalDateTime dateOfPurchases);

    Orders updateOrder(Orders order);

    List<Orders> findAll();

    Orders findById(Long id);


}