package by.itstep.springInternetShop.controller.impl;


import by.itstep.springInternetShop.service.OrderService;
import by.itstep.springInternetShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class DefaultControllerImpl {

    private ProductService productService;
    private OrderService orderService;

    @GetMapping("/")
    public String sayIndex(Model model, Principal principal) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        String userLogin = userDetails.getUsername();
        String userLogin;
        if (principal != null && principal.getName() != null) {
            userLogin = principal.getName();
        } else {
            userLogin = "Guest";
        }
        model.addAttribute("userName", userLogin);
        return "home/index";
    }

}
