package com.example.dao.repository;


import com.example.dao.entity.Orders;
import com.example.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    Page<Orders> findAll(Pageable pageable);

    boolean deleteByDateOfPurchase(LocalDateTime dateOfPurchase);




}

