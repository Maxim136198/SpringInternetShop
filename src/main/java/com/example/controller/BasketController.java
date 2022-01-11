package com.example.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/basket")
public interface BasketController {

    String saveToBasket(@PathVariable(name = "productId") Long productId);

}
