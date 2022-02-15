package com.example.service;



import com.example.dao.entity.Item;
import com.example.dao.entity.Orders;
import com.example.dao.entity.Product;
import com.example.dao.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Orders save(Orders order);

    void deleteById(Long id);

    void deleteByDateOfPurchases(LocalDateTime dateOfPurchases);

    Orders updateOrder(Orders order);

    List<Orders> findAll();

    Orders findById(Long id);

    void saveToOrder(Long productId);

    Page<Orders> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Orders createOrder(List<Item> basket, User user);


}