package com.example.controller.impl;


import com.example.controller.OrderController;
import com.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    private OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allOrders")
    public String getAllOrder(Model model){
        model.addAttribute("allOrders", orderService.findAll());
        return "order/allOrder";

    }

}
