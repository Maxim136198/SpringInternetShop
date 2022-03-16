package by.itstep.springInternetShop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/order")
public interface OrderController {

    String saveToBasket(@PathVariable(name = "productId") Long productId);

    String getAllOrder(Model model);

    String getListItems (Model model);

    String createOrder(Model model, Principal principal);

}
