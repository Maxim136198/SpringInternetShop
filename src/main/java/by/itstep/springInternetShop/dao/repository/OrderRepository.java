package by.itstep.springInternetShop.dao.repository;


import by.itstep.springInternetShop.dao.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAll(Pageable pageable);

    boolean deleteByDateOfPurchase(LocalDateTime dateOfPurchase);




}

