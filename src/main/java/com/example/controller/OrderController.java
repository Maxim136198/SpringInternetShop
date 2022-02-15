package com.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderController {

    String saveToBasket(@PathVariable(name = "productId") Long productId);
}
