package com.example.controller.impl;


import com.example.controller.BasketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DefaultBasketController implements BasketController {



    @GetMapping("")
    public String getBasket() {

    return "basket/basket";
    }


    @Override
    @PostMapping("/{productId}")
    public String saveToBasket(Long productId) {
//        log.info("saveToBasket: productId = {}", productId);

        return null;
    }
}
