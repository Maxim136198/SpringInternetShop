package com.example.service.impl;


import com.example.dao.entity.Item;
import com.example.dao.entity.Order;
import com.example.dao.entity.User;
import com.example.dao.repository.ItemRepository;
import com.example.dao.repository.OrderRepository;
import com.example.dao.repository.ProductRepository;
import com.example.dao.repository.UserRepository;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private  OrderRepository orderRepository;
    private  UserRepository userRepository;
    private  ItemRepository itemRepository;
    private  ProductRepository productRepository;

    @Override
    public Order save(Order order) {
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
    public Order update(Order order) {
        final Long id = order.getId();
        validate(id == null, "error.order.haveId");
        findById(id);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> findAllList() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {

        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    }

    @Override
    public void saveToOrder(Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String userLogin = userDetails.getUsername();

//        var user = userRepository.findByName(userLogin).orElseThrow(()-> new RuntimeException("User not found"));
//        var product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
//
//        Orders order = new Orders(null, user, LocalDateTime.now(), null,null);
//        orderRepository.save(order);
//
//        var orders = Orders.builder()
//                .customer(user)
//                .dateOfPurchase(LocalDateTime.now())
//                .items(null)
//                .build();
//        orderRepository.save(orders);
//
//        var item = Item.builder()
//                .orderId(orders)
//                .productId(product)
//                .build();
//
//        itemRepository.save(item);
//       orders.setItems(Set.of(item));
//       orderRepository.save(orders);

    }

    @Override
    public Order createOrder(List<Item> listItem, User user){
        Order order = new Order();

        order.setCustomer(user);
        order.setDateOfPurchase(LocalDateTime.now());
        order.setPurchasePrice(order.getPurchasePrice());
        double fullPrice = 0;


        for (Item item: listItem) {
            item.setOrder(order);
            fullPrice += item.getCount() * item.getProduct().getPrice();
            itemRepository.save(item);
        }

        order.setPurchasePrice(fullPrice);
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.orderRepository.findAll(pageable);
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

}

