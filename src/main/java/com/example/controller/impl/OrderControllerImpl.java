package com.example.controller.impl;


import com.example.controller.OrderController;
import com.example.dao.entity.*;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    private OrderService orderService;

    private UserService userService;

    private ProductService productService;

    private List<Item> itemList;

    public OrderControllerImpl(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/allOrders")
    public String getAllOrder(Model model) {
//        orderService.createOrder(itemList, userService.getUserName().getId());


        model.addAttribute("allOrders", orderService.findAll());
        return "order/allOrder";

    }

    @Override
    @PostMapping("/{productId}")
    public String saveToBasket(@PathVariable(name = "productId") Long productId) {
        Item item = null;
        if (itemList == null) {
            itemList = new ArrayList<>();
        } else {
            item = itemList.stream().filter(itemm -> itemm.getId().getProductId().equals(productId)).findFirst().orElse(null);

        }

        if (item != null) {
            item.setCount(item.getCount() + 1);
        } else {
            Product product = productService.findById(productId);
            item = new Item();
            item.setId(new ItemID(null, productId));
            item.setCount(1);
            item.setProduct(product);
            itemList.add(item);
        }
        System.out.println(itemList);
        System.out.println();

//        orderService.createOrder(itemList,userService.getUserName().getId());

        return "redirect:/product/allProduct";

    }

    @GetMapping("/basket")
    public String getListItems (Model model){
        model.addAttribute("items", itemList);
        return "basket/basket";
    }

    @PostMapping("/createOrder")
    public String createOrder(Model model, Principal principal){
        String userLogin = principal.getName();
        User user = userService.findByName(userLogin);

        Orders order = orderService.createOrder(itemList, user);
        model.addAttribute("order", order);
        return "basket/basket";
    }



//    @PostMapping("/{userId}")
//    public String createOrder(@PathVariable(name = "userId") Long userId, Model model) {
//        Orders order = new Orders();
//        order.setCustomer(userService.findById(userId));
//
//
//        model.addAttribute("order", order);
//        return "redirect:/product/allProduct";
//    }
}
