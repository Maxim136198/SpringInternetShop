package com.example.dao.repository;


import com.example.dao.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultBasketRepository extends JpaRepository<Basket, Long> {


}
