package com.example.dao.repository;


import com.example.dao.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAll();

    boolean deleteByDateOfPurchase(LocalDateTime dateOfPurchase);




}

