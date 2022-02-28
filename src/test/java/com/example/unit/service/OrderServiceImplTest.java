package com.example.unit.service;

import com.example.dao.entity.Order;
import com.example.dao.repository.OrderRepository;
import com.example.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

//    Order save(Order order);
//    void deleteById(Long id);
//    void deleteByDateOfPurchases(LocalDateTime dateOfPurchases);
//    Order updateOrder(Order order);
//    List<Order> findAll();
//    Order findById(Long id);

    @Test
    private void testSave() {
        final Order order = new Order();
        when(orderRepository.saveAndFlush(order)).thenReturn(order);
        assertEquals(orderService.save(order), order);
    }

    @Test
    private void testDeleteById() {
        final Order order = new Order();
        order.setId(1L);
        doNothing().when(orderRepository).deleteById(any(Long.class));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        assertDoesNotThrow(() -> orderService.deleteById(1L));
    }

    @Test
    private void testDeleteByDateOfPurchases() {
        final Order order = new Order();
        doNothing().when(orderRepository).deleteByDateOfPurchase(any(LocalDateTime.class));
        when(orderRepository.deleteByDateOfPurchase(any(LocalDateTime.class))).thenReturn(true);
//        assertEquals(orderService.deleteByDateOfPurchases(LocalDate.of(2000,10,24)), );
        assertDoesNotThrow(() -> orderService.deleteByDateOfPurchases(LocalDateTime.now()));
    }

    @Test
    private void testUpdate() {
        final Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.saveAndFlush(order)).thenReturn(order);
        assertEquals(orderService.update(order), order);
    }

    @Test
    private void testFindAll() {
        final List<Order> orderList = Collections.singletonList(new Order());
        when(orderRepository.findAll()).thenReturn(orderList);
        assertEquals(orderService.findAllList(), orderList);
    }

    @Test
    private void testFindById() {
        final Order order = new Order();
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));
        assertEquals(orderService.findById(1L), order);
    }
}
